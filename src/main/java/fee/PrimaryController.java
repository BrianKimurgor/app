package fee;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TableView<Fee> feeTable;
    @FXML
    private TableColumn<Fee, String> feeIdColumn;
    @FXML
    private TableColumn<Fee, String> feeNameColumn;
    @FXML
    private TableColumn<Fee, Double> feeAmountColumn;

    @FXML
    private void initialize() {
        feeIdColumn.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        feeNameColumn.setCellValueFactory(new PropertyValueFactory<>("feeName"));
        feeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("feeAmount"));

        feeTable.setItems(FXCollections.observableArrayList(FeeDB.getAllFees()));
    }



private Fee showFeeDialog(Fee fee) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("feeDialog.fxml")); // Create feeDialog.fxml for the form
        DialogPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle(fee == null ? "Add Fee" : "Edit Fee");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(page));

        FeeDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFee(fee);

        dialogStage.showAndWait();

        return controller.isConfirmed() ? controller.getFee() : null;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}


    @FXML
    private void handleDelete() {
        Fee selectedFee = feeTable.getSelectionModel().getSelectedItem();
        if (selectedFee != null) {
            boolean success = FeeDB.deleteFee(selectedFee.getFeeId());
            if (success) {
                feeTable.getItems().remove(selectedFee);
            } else {
                showAlert("Error", "Failed to delete the fee.");
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
