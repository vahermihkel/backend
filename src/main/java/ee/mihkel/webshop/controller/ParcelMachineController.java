package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.request.ParcelMachine;
import ee.mihkel.webshop.service.ParcelMachineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@Log4j2
public class ParcelMachineController {

    @Autowired
    ParcelMachineService parcelMachineService;

    @Operation(summary = "Saa Omniva ja SmartPost pakiautomaadid, riik standardiga ISO_3166-2")
    @GetMapping("parcel-machines/{country}")
    public ParcelMachine getParcelMachines(@PathVariable String country) throws Exception {
        log.info("Tehti pakiautomaatide võtmise päring");
        log.info(parcelMachineService);
        return parcelMachineService.getParcelMachines(country.toUpperCase());
    }
}
