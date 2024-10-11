package fee;

import java.math.BigDecimal;

public class Fee {
    private String feeId;
    private String feeName;
    private BigDecimal feeAmt;  // Use BigDecimal for fee amounts
    private String feeDesc;     // Include FeeDesc

    // Constructor
    public Fee(String feeId, String feeName, BigDecimal feeAmt, String feeDesc) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.feeAmt = feeAmt;
        this.feeDesc = feeDesc;
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

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeDesc() {
        return feeDesc;
    }

    public void setFeeDesc(String feeDesc) {
        this.feeDesc = feeDesc;
    }
}
