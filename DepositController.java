package main;

import java.util.HashMap;
import java.util.Map;

public class DepositController {

    // Example in-memory data (you can later replace this with file or database)
    private static final Map<String, Double> accounts = new HashMap<>();

    static {
        accounts.put("12345", 500.0);
        accounts.put("67890", 1000.0);
    }

    public boolean deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            double newBalance = accounts.get(accountNumber) + amount;
            accounts.put(accountNumber, newBalance);
            System.out.println("New balance for " + accountNumber + ": " + newBalance);
            return true;
        } else {
            return false; // account not found
        }
    }
}

