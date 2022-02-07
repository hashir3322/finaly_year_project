package orders;

public class OrderJson {
    private int order_id;
    private String ordered_med_name;
    private int ordered_med_price;
    private int ordered_med_qty;
    private int ordered_med_net;
    private String ordering_pharmacy;
    private String pharmacy_name;
    private String order_status;

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrdered_med_name() {
        return ordered_med_name;
    }

    public void setOrdered_med_name(String ordered_med_name) {
        this.ordered_med_name = ordered_med_name;
    }

    public int getOrdered_med_price() {
        return ordered_med_price;
    }

    public void setOrdered_med_price(int ordered_med_price) {
        this.ordered_med_price = ordered_med_price;
    }

    public int getOrdered_med_qty() {
        return ordered_med_qty;
    }

    public void setOrdered_med_qty(int ordered_med_qty) {
        this.ordered_med_qty = ordered_med_qty;
    }

    public int getOrdered_med_net() {
        return ordered_med_net;
    }

    public void setOrdered_med_net(int ordered_med_net) {
        this.ordered_med_net = ordered_med_net;
    }

    public String getOrdering_pharmacy() {
        return ordering_pharmacy;
    }

    public void setOrdering_pharmacy(String ordering_pharmacy) {
        this.ordering_pharmacy = ordering_pharmacy;
    }
}
