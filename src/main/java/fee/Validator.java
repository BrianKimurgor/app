package fee;

public class Validator {

    public static boolean validateFeeId(String feeId) {
        return feeId != null && !feeId.trim().isEmpty();
    }

    public static boolean validateFeeName(String feeName) {
        return feeName != null && !feeName.trim().isEmpty();
    }

    public static boolean validateFeeAmount(String feeAmount) {
        try {
            double amount = Double.parseDouble(feeAmount);
            return amount >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
