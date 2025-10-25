package main;

import javafx.beans.property.*;

public class Account {
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public void setAccountNumber(String value) {
        accountNumber.set(value);
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public double getBalance() {
        return balance.get();
    }

    public void setBalance(double value) {
        balance.set(value);
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }
}

