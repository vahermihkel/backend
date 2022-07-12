package ee.mihkel.webshop.exceptions;

import ee.mihkel.webshop.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.NoSuchElementException;

// läheb iga kontrolleri külge, et kinni püüda exceptioneid (erroreid)
@ControllerAdvice
public class ErrorHandler {

    // püüab kinni errori
    @ExceptionHandler                               // siin on error kirjas
        //  mida ta selle asemel tagastab
    public ResponseEntity<CustomError> handleError(NoSuchElementException e) {
        CustomError customError = new CustomError();
        customError.setTimeStamp(new Date());
        customError.setMessage("Otsitud elementi ei leitud");
        customError.setError(HttpStatus.NOT_FOUND);
        customError.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomError> handleError(ProductNotFoundException e) {
        CustomError customError = new CustomError();
        customError.setTimeStamp(new Date());
        customError.setMessage("Otsitud toodet ei leitud");
        customError.setError(HttpStatus.NOT_FOUND);
        customError.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomError> handleError(HttpMessageNotReadableException e) {
        CustomError customError = new CustomError();
        customError.setTimeStamp(new Date());
        customError.setMessage("Uut elementi lisades on element puudu jäänud");
        customError.setError(HttpStatus.BAD_REQUEST);
        customError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomError> handleError(HttpRequestMethodNotSupportedException e) {
        CustomError customError = new CustomError();
        customError.setTimeStamp(new Date());
        customError.setMessage("Vale meetodi tüüp");
        customError.setError(HttpStatus.BAD_REQUEST);
        customError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomError> handleError(MethodArgumentTypeMismatchException e) {
        CustomError customError = new CustomError();
        customError.setTimeStamp(new Date());
        customError.setMessage("Sisestasid numbri asemel muu sümboli");
        customError.setError(HttpStatus.BAD_REQUEST);
        customError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }
}
