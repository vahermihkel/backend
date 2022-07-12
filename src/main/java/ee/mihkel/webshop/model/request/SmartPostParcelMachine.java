package ee.mihkel.webshop.model.request;

import lombok.Data;

@Data
public class SmartPostParcelMachine {
    public int active;
    public String address;
    public String city;
    public String created_date;
    public int group_id;
    public String group_name;
    public int group_sort;
    public String name;
    public String opened;
    public int place_id;
    public String updated_date;
    public String description;
}
