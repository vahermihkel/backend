package ee.mihkel.webshop.service;

import ee.mihkel.webshop.cache.ProductCache;
import ee.mihkel.webshop.exceptions.ProductNotFoundException;
import ee.mihkel.webshop.model.Order;
import ee.mihkel.webshop.model.PaymentStatus;
import ee.mihkel.webshop.model.Person;
import ee.mihkel.webshop.model.Product;
import ee.mihkel.webshop.model.request.EveryPayData;
import ee.mihkel.webshop.model.request.EveryPayResponse;
import ee.mihkel.webshop.repository.OrderRepository;
import ee.mihkel.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    ProductCache productCache;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrderRepository orderRepository;

    @Value("${everypay.url}")
    String everyPayUrl;

    @Value("${everypay.auth}")
    String everyPayAuth;

    @Value("${everypay.username}")
    String everyPayUsername;

    @Value("${everypay.account}")
    String everyPayAccount;

    @Value("${everypay.customer-url}")
    String everyPayCustomerUrl;

    public List<Product> getOriginalProducts(List<Product> products) {
        return products.stream()
                .map(e -> {
                    try {
                        return productCache.getProduct(e.getId());
                    } catch (ExecutionException executionException) {
                        throw new RuntimeException();
                    }
                })
                .collect(Collectors.toList());
    }
    //        List<Product> originalProducts2 = new ArrayList<>();
//        for (Product p: products) {
//            Product databaseProduct = productRepository.findById(p.getId()).get();
//            originalProducts2.add(databaseProduct);
//        }

    public double calculateOrderSum(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
//    double orderSum2 = 0;
//        for (Product p: originalProducts)  {
//            orderSum2 += p.getPrice();
//        }

    public Long saveOrder(List<Product> products, double orderSum, String personCode) {
        Order order = new Order();
        order.setCreatedDate(new Date());
        order.setProducts(products);
        order.setPaymentStatus(PaymentStatus.INITIAL);
        order.setSum(orderSum);
        Person person = personRepository.findById(personCode).get();
        order.setPerson(person);
        Order newOrder = orderRepository.save(order);
        return newOrder.getId();
    }

    public String pay(double orderSum, Long orderId) {

        RestTemplate restTemplate = new RestTemplate();
        String url = everyPayUrl + "/payments/oneoff";
        EveryPayData everyPayData = setEveryPayData(orderSum, orderId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + everyPayAuth);
        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData,headers);
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, EveryPayResponse.class);
        return response.getBody().payment_link;
    }

    private EveryPayData setEveryPayData(double orderSum, Long orderId) {
        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username(everyPayUsername);
        everyPayData.setAccount_name(everyPayAccount);
        everyPayData.setAmount(orderSum);
        everyPayData.setOrder_reference(orderId);
        everyPayData.setNonce("asdasdasdas"+Math.random()+new Date());
        everyPayData.setTimestamp(ZonedDateTime.now().toString());
        everyPayData.setCustomer_url(everyPayCustomerUrl);
        return  everyPayData;
    }
}
