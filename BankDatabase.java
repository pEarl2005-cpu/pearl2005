package main;

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {

    // Shared map of accountNumber → balance
    private static final Map<String, Double> accounts = new HashMap<>();

    static {
        // Example accounts (you can add more or load from file later)
        accounts.put("12345", 500.0);
        accounts.put("67890", 1000.0);
        accounts.put("24680", 250.0);
    }

    // Get all accounts (for AccountsView)
    public static Map<String, Double> getAccounts() {
        return accounts;
    }

    // Deposit method
    public static boolean deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, accounts.get(accountNumber) + amount);
            return true;
        }
        return false;
    }

    // Withdraw method
    public static boolean withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            double currentBalance = accounts.get(accountNumber);
            if (currentBalance >= amount) {
                accounts.put(accountNumber, currentBalance - amount);
                return true;
            }
        }
        return false;
    }

    //  New method to apply interest to all accounts
    public static void applyInterest(double ratePercent) {
        for (String acc : accounts.keySet()) {
            double current = accounts.get(acc);
            double newBalance = current + (current * ratePercent / 100);
            accounts.put(acc, newBalance);
        }
    }
}


