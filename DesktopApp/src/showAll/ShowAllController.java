package showAll;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import requestHelper.GetRequest;



import java.io.IOException;
import java.net.http.HttpResponse;


public class ShowAllController {

    @FXML
    private TableView<Medicine> medicineTable;

    @FXML
    private TableColumn<Medicine, String> medIdCol;

    @FXML
    private TableColumn<Medicine, String> medNameCol;

    @FXML
    private TableColumn<Medicine, String> medPriceCol;

    @FXML
    private TableColumn<Medicine, String> medNetPriceCol;

    @FXML
    private TableColumn<Medicine, String> medManufacturerCol;

    @FXML
    private TableColumn<Medicine, String> medQtyCol;

    Medicine medicine = null;
    ObservableList<Medicine> medicineList = FXCollections.observableArrayList();


    public void initialize() throws IOException, InterruptedException {
        dataLoader();
    }
    public void dataLoader() throws IOException, InterruptedException {

        refreshTable();

        medIdCol.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        medNameCol.setCellValueFactory(new PropertyValueFactory<>("med_name"));
        medPriceCol.setCellValueFactory(new PropertyValueFactory<>("med_price"));
        medNetPriceCol.setCellValueFactory(new PropertyValueFactory<>("med_net_price"));
        medManufacturerCol.setCellValueFactory(new PropertyValueFactory<>("med_manufacturer"));
        medQtyCol.setCellValueFactory(new PropertyValueFactory<>("med_quantity"));

    }
    public void refreshTable() throws IOException, InterruptedException {
        GetRequest getRequest = new GetRequest();
        HttpResponse<String> response = getRequest.requestSender("http://localhost/MIS/API/fetch-all.php");

        Gson gson = new Gson();

        MedicineJson[] medJsonArray = gson.fromJson(response.body(),MedicineJson[].class);
        medicineList.clear();
        for(MedicineJson med : medJsonArray) {
            medicineList.add(new Medicine(med.getMed_id(),med.getMed_name(),med.getMed_price(),med.getMed_net_price(),med.getMed_manufacturer(),med.getMed_quantity()));
            medicineTable.setItems(medicineList);
        }
    }
    
}
