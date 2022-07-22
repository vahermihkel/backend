package ee.mihkel.webshop.model.request;

import lombok.Data;

@Data
public class CheckPaymentData {
    private Long order_reference;
    private String payment_reference;
}
