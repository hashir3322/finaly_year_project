package bill;

public class BillMedicine {
    private int billId;
    private String billMedName;
    private int billPrice;
    private int billNetPrice;
    private int billQty;

    public BillMedicine(int billId, String billMedName, int billPrice, int billNetPrice, int billQty) {
        this.billId = billId;
        this.billMedName = billMedName;
        this.billPrice = billPrice;
        this.billNetPrice = billNetPrice;
        this.billQty = billQty;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillMedName() {
        return billMedName;
    }

    public void setBillMedName(String billMedName) {
        this.billMedName = billMedName;
    }

    public int getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(int billPrice) {
        this.billPrice = billPrice;
    }

    public int getBillNetPrice() {
        return billNetPrice;
    }

    public void setBillNetPrice(int billNetPrice) {
        this.billNetPrice = billNetPrice;
    }

    public int getBillQty() {
        return billQty;
    }

    public void setBillQty(int billQty) {
        this.billQty = billQty;
    }
}
