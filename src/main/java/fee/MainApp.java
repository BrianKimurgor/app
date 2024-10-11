package fee;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;

public class MainApp extends Application {

    private TableView<Fee> feeTable;
    private ObservableList<Fee> feeList;

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        feeTable = new TableView<>();
        feeList = FXCollections.observableArrayList(FeeDB.getAllFees());
        feeTable.setItems(feeList);

        // Define columns
        TableColumn<Fee, String> feeIdColumn = new TableColumn<>("Fee ID");
        feeIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeeId()));

        TableColumn<Fee, String> feeNameColumn = new TableColumn<>("Fee Name");
        feeNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeeName()));

        TableColumn<Fee, BigDecimal> feeAmountColumn = new TableColumn<>("Fee Amount");
        feeAmountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFeeAmt()));

        TableColumn<Fee, String> feeDescColumn = new TableColumn<>("Fee Description");
        feeDescColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeeDesc()));

        // Add columns to the table
        feeTable.getColumns().addAll(feeIdColumn, feeNameColumn, feeAmountColumn, feeDescColumn);

        // Buttons for adding, editing, and deleting fees
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAdd());

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> handleEdit());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        // Layout
        VBox vbox = new VBox(10, feeTable, new HBox(10, addButton, editButton, deleteButton));
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fees Management");
        primaryStage.show();
    }

    private void handleAdd() {
        Fee newFee = showFeeDialog(null);  // Null means adding a new fee
        if (newFee != null) {
            boolean success = FeeDB.addFee(newFee);
            if (success) {
                System.out.println("Fee added successfully!");  // Debug message
                feeTable.getItems().add(newFee);
            } else {
                showAlert("Error", "Failed to add the fee.");
            }
        } else {
            System.out.println("Add Fee dialog was canceled or invalid input.");  // Debug message
        }
    }

    private void handleEdit() {
        Fee selectedFee = feeTable.getSelectionModel().getSelectedItem();
        if (selectedFee != null) {
            Fee editedFee = showFeeDialog(selectedFee);  // Pass the selected fee for editing
            if (editedFee != null) {
                boolean success = FeeDB.updateFee(editedFee);
                if (success) {
                    feeTable.refresh();  // Refresh table to show updated values
                } else {
                    showAlert("Error", "Failed to edit the fee.");
                }
            }
        } else {
            showAlert("No Selection", "Please select a fee to edit.");
        }
    }

    private void handleDelete() {
        Fee selectedFee = feeTable.getSelectionModel().getSelectedItem();
        if (selectedFee != null) {
            boolean success = FeeDB.deleteFee(selectedFee.getFeeId());
            if (success) {
                feeList.remove(selectedFee);  // Remove the fee from the list
            } else {
                showAlert("Error", "Failed to delete the fee.");
            }
        } else {
            showAlert("No Selection", "Please select a fee to delete.");
        }
    }

    // Show add/edit dialog
    private Fee showFeeDialog(Fee fee) {
        try {
            System.out.println("Loading Fee Dialog FXML...");  // Debug line
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("feeDialog.fxml"));
            GridPane page = loader.load();  // Change DialogPane to GridPane

            Stage dialogStage = new Stage();
            dialogStage.setTitle(fee == null ? "Add Fee" : "Edit Fee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(page));

            FeeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFee(fee);

            dialogStage.showAndWait();
            return controller.isConfirmed() ? controller.getFee() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
