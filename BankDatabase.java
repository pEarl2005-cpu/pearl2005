package main;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BankDatabase {

    
    static {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1");
             Statement stmt = conn.createStatement()) {

            // Create table for accounts
            stmt.executeUpdate("""
                CREATE TABLE accounts (
                    account_number VARCHAR(20) PRIMARY KEY,
                    balance DOUBLE
                );
            """);

            
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO accounts VALUES ('12345', 2000.0)");
                stmt.executeUpdate("INSERT INTO accounts VALUES ('67890', 1000.0)");
                stmt.executeUpdate("INSERT INTO accounts VALUES ('24680', 250.0)");
                System.out.println("Sample accounts created in H2 database.");
            }

        } catch (SQLException e) {
        }
    }

    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1");
    }

    // Get all accounts 
    public static Map<String, Double> getAccounts() {
        Map<String, Double> accounts = new HashMap<>();
        String sql = "SELECT account_number, balance FROM accounts";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                accounts.put(rs.getString("account_number"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
        }

        return accounts;
    }

    // Deposit method
    public static boolean deposit(String accountNumber, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            int rows = pstmt.executeUpdate();
            return rows > 0; // true if updated successfully
        } catch (SQLException e) {
        }
        return false;
    }

    // Withdraw method
    public static boolean withdraw(String accountNumber, double amount) {
        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, accountNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setDouble(1, amount);
                        updateStmt.setString(2, accountNumber);
                        updateStmt.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
        }

        return false;
    }

    // Apply interest to all accounts
    public static void applyInterest(double ratePercent) {
        String sql = "UPDATE accounts SET balance = balance + (balance * ? / 100)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, ratePercent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
