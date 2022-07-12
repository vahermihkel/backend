package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.cache.ProductCache;
import ee.mihkel.webshop.model.Order;
import ee.mihkel.webshop.model.PaymentStatus;
import ee.mihkel.webshop.model.Person;
import ee.mihkel.webshop.model.Product;
import ee.mihkel.webshop.model.request.EveryPayData;
import ee.mihkel.webshop.model.request.EveryPayLink;
import ee.mihkel.webshop.repository.OrderRepository;
import ee.mihkel.webshop.repository.PersonRepository;
import ee.mihkel.webshop.repository.ProductRepository;
import ee.mihkel.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3000")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ProductCache productCache;

    @Autowired
    OrderService orderService;

    @PostMapping("payment/{personCode}") // localhost:8080/payment/3131231
    public EveryPayLink payment(@PathVariable String personCode, @RequestBody List<Product> products) throws RuntimeException  {

        List<Product> originalProducts = orderService.getOriginalProducts(products);
        double orderSum = orderService.calculateOrderSum(originalProducts);
        Long orderId = orderService.saveOrder(originalProducts, orderSum, personCode);

        EveryPayLink everyPayLink = new EveryPayLink();
        String paymentLink = orderService.pay(orderSum, orderId);
        everyPayLink.setLink(paymentLink);

        return everyPayLink;
    }

    @GetMapping("order")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("order/{id}")
    public Order getOrder(@PathVariable Long id) {
        System.out.println("võtsin orderi");
        return orderRepository.findById(id).get();
    }

    @GetMapping("person-order/{personCode}")
    public List<Order> getPersonOrders(@PathVariable String personCode) {
        System.out.println("võtsin orderi personcode kaudu");
        Person person = personRepository.findById(personCode).get();
        return orderRepository.getAllByPerson(person);
    }
}
