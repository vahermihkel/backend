package ee.mihkel.webshop.service;

import ee.mihkel.webshop.model.request.OmnivaParcelMachine;
import ee.mihkel.webshop.model.request.ParcelMachine;
import ee.mihkel.webshop.model.request.SmartPostParcelMachine;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ParcelMachineService {

    @Autowired
    RestTemplate restTemplate;

    String omnivaUrl = "https://www.omniva.ee/locations.json";
    String smartPostUrl = "https://pakiautomaadid-default-rtdb.europe-west1.firebasedatabase.app/smartpost.json";

    public ParcelMachine getParcelMachines(String country) throws Exception {
        ParcelMachine parcelMachine = new ParcelMachine();
        //RestTemplate restTemplate = new RestTemplate();
        log.info(restTemplate);

        parcelMachine.setOmnivaParcelMachines(fetchOmnivaParcelMachines(restTemplate, country));

        if (country.equals("EE")) {
            parcelMachine.setSmartPostParcelMachines(fetchSmartPostParcelMachines(restTemplate));
        } else {
            parcelMachine.setSmartPostParcelMachines(new ArrayList<>());
        }
        return parcelMachine;
    }

    private List<OmnivaParcelMachine> fetchOmnivaParcelMachines(RestTemplate restTemplate, String country) throws Exception {
        ResponseEntity<OmnivaParcelMachine[]> omnivaResponse = restTemplate.exchange(
                omnivaUrl, HttpMethod.GET, null, OmnivaParcelMachine[].class);

        if (omnivaResponse.getBody() != null) {
            OmnivaParcelMachine[] omnivaParcelMachines = omnivaResponse.getBody();
            List<OmnivaParcelMachine> omnivaParcelMachineList = Arrays.asList(omnivaParcelMachines);
            omnivaParcelMachineList = omnivaParcelMachineList.stream()
                                                            .filter(e -> e.a0_NAME.equals(country))
                                                            .collect(Collectors.toList());
            return omnivaParcelMachineList;
        } else {
            log.error("Omniva pakiautomaatide võtmisel ei saadud kätte pakiautomaate");
            throw new Exception();
        }
    }

    private List<SmartPostParcelMachine> fetchSmartPostParcelMachines(RestTemplate restTemplate) throws Exception {
        ResponseEntity<SmartPostParcelMachine[]> smartPostResponse = restTemplate.exchange(
                smartPostUrl, HttpMethod.GET, null, SmartPostParcelMachine[].class);

        if (smartPostResponse.getBody() != null) {
            SmartPostParcelMachine[] smartPostParcelMachines = smartPostResponse.getBody();
            return Arrays.asList(smartPostParcelMachines);
        } else {
            log.error("SmartPost pakiautomaatide võtmisel ei saadud kätte pakiautomaate");
            throw new Exception();
        }
    }
}
