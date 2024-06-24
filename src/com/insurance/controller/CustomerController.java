package com.insurance.controller;

import com.insurance.model.Customer;
import com.insurance.model.Policy;
import com.insurance.model.Payment;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerController {
    private PolicyController policyController;
    private PaymentController paymentController;
    private AtomicInteger paymentIdCounter = new AtomicInteger(1); // Counter for generating payment IDs

    public CustomerController(PolicyController policyController, PaymentController paymentController) {
        this.policyController = policyController;
        this.paymentController = paymentController;
    }

    public void applyForPolicy(Customer customer, Policy policy) {
        // Currently empty, should include logic for a customer to apply for a policy.
    }

    public List<Policy> viewPolicies(Customer customer) {
        return policyController.getAllPolicies();
    }

    public Policy getPolicy(int policyId) {
        return policyController.getPolicy(policyId);
    }

    public void makePayment(Payment payment) {
        paymentController.addPayment(payment); // Ensure the admin controller also tracks the payment
    }

    public List<Payment> viewPayments(Customer customer) {
        return paymentController.getAllPayments();
    }

    public int generatePaymentId() {
        return paymentIdCounter.getAndIncrement(); // Generate and increment payment ID
    }
}
