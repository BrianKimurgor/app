package fee;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class FeeDialogController {

    @FXML
    private TextField feeIdField;
    @FXML
    private TextField feeNameField;
    @FXML
    private TextField feeAmountField;
    @FXML
    private TextField feeDescField;  // Add feeDesc field

    private Stage dialogStage;
    private Fee fee;
    private boolean confirmed = false;

    @FXML
    private void initialize() {
        // No initialization required
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFee(Fee fee) {
        if (fee != null) {
            this.fee = fee;
            feeIdField.setText(fee.getFeeId());
            feeNameField.setText(fee.getFeeName());
            feeAmountField.setText(fee.getFeeAmt().toString());  // Use BigDecimal's toString method
            feeDescField.setText(fee.getFeeDesc());  // Set FeeDesc
        } else {
            this.fee = new Fee("", "", BigDecimal.ZERO, "");  // Default fee object for adding
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Fee getFee() {
        return fee;  // Return the fee object
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            // Set fee details only if input is valid
            fee.setFeeId(feeIdField.getText());
            fee.setFeeName(feeNameField.getText());
            fee.setFeeAmt(new BigDecimal(feeAmountField.getText()));  // Parse fee amount as BigDecimal
            fee.setFeeDesc(feeDescField.getText());  // Set fee description
            confirmed = true;  // Confirm the dialog
            dialogStage.close();  // Close the dialog
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();  // Close the dialog without confirmation
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (feeIdField.getText() == null || feeIdField.getText().isEmpty()) {
            errorMessage += "No valid fee ID!\n";
        }
        if (feeNameField.getText() == null || feeNameField.getText().isEmpty()) {
            errorMessage += "No valid fee name!\n";
        }
        if (feeAmountField.getText() == null || feeAmountField.getText().isEmpty()) {
            errorMessage += "No valid fee amount!\n";
        } else {
            try {
                new BigDecimal(feeAmountField.getText());  // Validate BigDecimal format
            } catch (NumberFormatException e) {
                errorMessage += "No valid fee amount (must be a number)!\n";
            }
        }
        if (errorMessage.isEmpty()) {
            return true;  // Input is valid
        } else {
            showAlert("Invalid Fields", errorMessage);  // Show alert for invalid input
            return false;  // Input is not valid
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
