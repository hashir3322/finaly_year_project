package insert;

public class InsertToJson {

    private String med_name;
    private String med_price;
    private String med_net_price;
    private String med_manufacturer;
    private String med_quantity;

    public InsertToJson(String med_name, String med_price, String med_net_price, String med_manufacturer, String med_quantity) {
        this.med_name = med_name;
        this.med_price = med_price;
        this.med_net_price = med_net_price;
        this.med_manufacturer = med_manufacturer;
        this.med_quantity = med_quantity;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_price() {
        return med_price;
    }

    public void setMed_price(String med_price) {
        this.med_price = med_price;
    }

    public String getMed_net_price() {
        return med_net_price;
    }

    public void setMed_net_price(String med_net_price) {
        this.med_net_price = med_net_price;
    }

    public String getMed_manufacturer() {
        return med_manufacturer;
    }

    public void setMed_manufacturer(String med_manufacturer) {
        this.med_manufacturer = med_manufacturer;
    }

    public String getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(String med_quantity) {
        this.med_quantity = med_quantity;
    }
}

