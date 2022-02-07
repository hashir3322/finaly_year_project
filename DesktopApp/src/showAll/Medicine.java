package showAll;

public class Medicine {
    private int med_id;
    private String med_name;
    private int med_price;
    private int med_net_price;
    private String med_manufacturer;
    private int med_quantity;

    public Medicine(int med_id, String med_name, int med_price, int med_net_price, String med_manufacturer, int med_quantity) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.med_price = med_price;
        this.med_net_price = med_net_price;
        this.med_manufacturer = med_manufacturer;
        this.med_quantity = med_quantity;
    }


    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public int getMed_price() {
        return med_price;
    }

    public void setMed_price(int med_price) {
        this.med_price = med_price;
    }

    public int getMed_net_price() {
        return med_net_price;
    }

    public void setMed_net_price(int med_net_price) {
        this.med_net_price = med_net_price;
    }

    public String getMed_manufacturer() {
        return med_manufacturer;
    }

    public void setMed_manufacturer(String med_manufacturer) {
        this.med_manufacturer = med_manufacturer;
    }

    public int getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(int med_quantity) {
        this.med_quantity = med_quantity;
    }
}
