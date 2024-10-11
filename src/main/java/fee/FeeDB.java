package fee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeeDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travelexperts";
    private static final String USER = "root";
    private static final String PASSWORD = "6979samZ.@"; // Update this to your actual password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static List<Fee> getAllFees() {
        List<Fee> fees = new ArrayList<>();
        String query = "SELECT * FROM fees";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                fees.add(new Fee(rs.getString("FeeId"), rs.getString("FeeName"), rs.getDouble("FeeAmount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fees;
    }

    public static boolean addFee(Fee fee) {
        String query = "INSERT INTO Fees (FeeId, FeeName, FeeAmount) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fee.getFeeId());
            pstmt.setString(2, fee.getFeeName());
            pstmt.setDouble(3, fee.getFeeAmount());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateFee(Fee fee) {
        String query = "UPDATE Fees SET FeeName = ?, FeeAmount = ? WHERE FeeId = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fee.getFeeName());
            pstmt.setDouble(2, fee.getFeeAmount());
            pstmt.setString(3, fee.getFeeId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFee(String feeId) {
        String query = "DELETE FROM Fees WHERE FeeId = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, feeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
