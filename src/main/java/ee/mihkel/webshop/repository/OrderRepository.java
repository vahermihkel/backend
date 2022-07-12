package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.model.Order;
import ee.mihkel.webshop.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByPerson(Person person);
}
