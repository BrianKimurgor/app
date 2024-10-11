package fee;

public class Fee {
    private String feeId;
    private String feeName;
    private double feeAmount;

    // Constructor
    public Fee(String feeId, String feeName, double feeAmount) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.feeAmount = feeAmount;
    }

    // Getters and Setters
    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
}
