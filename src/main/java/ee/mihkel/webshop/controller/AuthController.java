package ee.mihkel.webshop.controller;

//import ee.mihkel.webshop.configuration.TokeniGenereerija;
import ee.mihkel.webshop.model.Person;
import ee.mihkel.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    PersonRepository personRepository;

//    @Autowired
//    TokeniGenereerija tokeniGenereerija;

    @PostMapping("signup")  // localhost:8080/signup    --> singup
    public String signup(@RequestBody Person person) {
        personRepository.save(person);
        return "Registreerimine edukas!";
    }

    @PostMapping("login")  // localhost:8080/signup    --> singup
    public String login() {
//        String token = tokeniGenereerija.createAuthToken();
        String token = "";
        System.out.println("TOKEN:");
        System.out.println(token);
        return token;
    }
}
