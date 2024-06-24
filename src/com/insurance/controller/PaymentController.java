package com.insurance.controller;

import com.insurance.model.Payment;

import java.util.List;

public interface PaymentController {
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int paymentId);
    Payment getPayment(int paymentId);
    List<Payment> getAllPayments();
}
