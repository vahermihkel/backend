package ee.mihkel.webshop.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ParcelMachine {
    List<OmnivaParcelMachine> omnivaParcelMachines;
    List<SmartPostParcelMachine> smartPostParcelMachines;
}
