package bill;

public class BillMedicineToJson {
    private int billId;
    private String billMedName;
    private int billPrice;
    private int billNetPrice;
    private int billQty;

    public BillMedicineToJson(int billId, String billMedName, int billPrice, int billNetPrice, int billQty) {
        this.billId = billId;
        this.billMedName = billMedName;
        this.billPrice = billPrice;
        this.billNetPrice = billNetPrice;
        this.billQty = billQty;
    }

    public int getBillId() {
        return billId;
    }

    public String getBillMedName() {
        return billMedName;
    }

    public int getBillPrice() {
        return billPrice;
    }

    public int getBillNetPrice() {
        return billNetPrice;
    }

    public int getBillQty() {
        return billQty;
    }
}
