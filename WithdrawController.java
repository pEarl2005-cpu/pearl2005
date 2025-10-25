package main;

import java.util.HashMap;
import java.util.Map;

public class WithdrawController {

    // Simulate existing accounts (shared data source)
    private static final Map<String, Double> accounts = new HashMap<>();

    static {
        accounts.put("12345", 500.0);
        accounts.put("67890", 1000.0);
    }

    public boolean withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            double currentBalance = accounts.get(accountNumber);

            if (currentBalance >= amount) {
                double newBalance = currentBalance - amount;
                accounts.put(accountNumber, newBalance);
                System.out.println("New balance for " + accountNumber + ": " + newBalance);
                return true;
            } else {
                System.out.println("Insufficient funds for " + accountNumber);
                return false;
            }
        } else {
            System.out.println("Account not found: " + accountNumber);
            return false;
        }
    }
}

