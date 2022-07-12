package ee.mihkel.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@SequenceGenerator(name="orderSeq", initialValue=514234, allocationSize=1)
public class Order { // order tabel on juba reserveeritud (user)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "orderSeq")
    private Long id;
    private double sum;
    private Date createdDate;
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Person person;

    @ManyToMany
    List<Product> products;
}
