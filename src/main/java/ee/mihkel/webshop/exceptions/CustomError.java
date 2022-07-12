package ee.mihkel.webshop.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class CustomError {
    private Date timeStamp;
    private int statusCode;
    private HttpStatus error;
    private String message;
}
