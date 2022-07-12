package ee.mihkel.webshop.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StringController {
    List<String> autod = new ArrayList<>(Arrays.asList("Audi", "Bmw"));

    @PostMapping("lisa-auto/{uusAuto}") // localhost:8080/lisa-auto/Ford     POST
    public List<String> lisaAuto(@PathVariable String uusAuto) {
        autod.add(uusAuto);
        return autod;
    }

    @DeleteMapping("kustuta-auto/{j2rjekorraNumber}") // localhost:8080/kustuta-auto/1     DELETE
    public List<String> lisaAuto(@PathVariable int j2rjekorraNumber) {
        autod.remove(j2rjekorraNumber);
        return autod;
    }



    @GetMapping("tere")  // localhost:8080/tere
    public String ytleTere() {
        return "Tere";
    }

    @GetMapping("nimi/{sisestatudNimi}")  // localhost:8080/nimi/Kaarel
    public String ytleTereNimele(@PathVariable String sisestatudNimi) {
        return "Tere " + sisestatudNimi;
    }

    @GetMapping("liida/{nr1}/{nr2}")  // localhost:8080/liida/3/8   ---> 11
    public int liida(@PathVariable int nr1, @PathVariable int nr2) {
        return nr1 + nr2;
    }
}
