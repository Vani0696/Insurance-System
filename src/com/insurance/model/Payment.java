package com.insurance.model;

public class Payment {
    private int paymentId;
    private int policyId;
    private String username;
    private double amount;

    public Payment(int paymentId, int policyId, String username, double amount) {
        this.paymentId = paymentId;
        this.policyId = policyId;
        this.username = username;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
