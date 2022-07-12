package ee.mihkel.webshop.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class EveryPayData {
    public String api_username;
    public String account_name;
    public double amount;
    public Long order_reference;
    public String nonce;
    public String timestamp;
    public String customer_url;
}
