package com.insurance.controller;

import com.insurance.model.Payment;
import com.insurance.model.Policy;
import com.insurance.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminControllerImpl implements UserController, PolicyController, PaymentController {
    private List<User> users = new ArrayList<>();
    private List<Policy> policies = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                return;
            }
        }
    }

    @Override
    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    @Override
    public void updatePolicy(Policy policy) {
        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).getPolicyId() == policy.getPolicyId()) {
                policies.set(i, policy);
                return;
            }
        }
    }

    @Override
    public void deletePolicy(int policyId) {
        policies.removeIf(policy -> policy.getPolicyId() == policyId);
    }

    @Override
    public Policy getPolicy(int policyId) {
        for (Policy policy : policies) {
            if (policy.getPolicyId() == policyId) {
                return policy;
            }
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policies;
    }

    @Override
    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == payment.getPaymentId()) {
                payments.set(i, payment);
                return;
            }
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        boolean removed = payments.removeIf(payment -> payment.getPaymentId() == paymentId);
        if (!removed) {
            System.out.println("Payment ID not found.");
        }
    }

    @Override
    public Payment getPayment(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }
}
