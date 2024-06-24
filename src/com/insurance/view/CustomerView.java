package com.insurance.view;

import com.insurance.model.Customer;
import com.insurance.model.Payment;
import com.insurance.model.Policy;
import com.insurance.controller.CustomerController;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private Scanner scanner = new Scanner(System.in);
    private CustomerController customerController;

    public CustomerView(CustomerController customerController) {
        this.customerController = customerController;
    }

    public void displayCustomerMenu(Customer customer) {
        while (true) {
            System.out.println("Customer Dashboard");
            System.out.println("1. View Policies");
            System.out.println("2. Apply for Policy");
            System.out.println("3. Make Payment");
            System.out.println("4. View Payments");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewPolicies();
                    break;
                case 2:
                    applyForPolicy(customer);
                    break;
                case 3:
                    makePayment(customer);
                    break;
                case 4:
                    viewPayments(customer);
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewPolicies() {
        List<Policy> policies = customerController.viewPolicies(null); // Fetch all policies
        if (policies.isEmpty()) {
            System.out.println("No policies available.");
        } else {
            for (Policy policy : policies) {
                System.out.println("================================");
                System.out.println("Policy ID: " + policy.getPolicyId());
                System.out.println("Name: " + policy.getPolicyName());
                System.out.println("Category: " + policy.getCategory());
                System.out.println("Sub-Category: " + policy.getSubCategory());
                System.out.println("Price: " + policy.getPrice());
                System.out.println("================================");
            }
        }
    }

    private void applyForPolicy(Customer customer) {
        try {
            System.out.println("Enter Policy ID to apply for:");
            int policyId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Policy policy = customerController.getPolicy(policyId); // Fetch the policy
            if (policy != null) {
                customerController.applyForPolicy(customer, policy);
                System.out.println("Applied for policy: " + policy.getPolicyName());
            } else {
                System.out.println("Policy not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void makePayment(Customer customer) {
        try {
            int paymentId = customerController.generatePaymentId(); // Generate a new payment ID

            System.out.println("Enter Policy ID:");
            int policyId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Policy policy = customerController.getPolicy(policyId); // Fetch the policy
            if (policy == null) {
                System.out.println("Policy not found.");
                return;
            }

            System.out.println("Enter Amount (Policy Price: " + policy.getPrice() + "):");
            if (!scanner.hasNextDouble()) {
                throw new IllegalArgumentException("Invalid amount. Please enter a numerical value.");
            }
            double amount = scanner.nextDouble();

            if (amount != policy.getPrice()) {
                System.out.println("Incorrect amount. The price for the policy is: " + policy.getPrice());
                return;
            }

            Payment payment = new Payment(paymentId, policyId, customer.getUsername(), amount);
            customerController.makePayment(payment);

            System.out.println("Payment made successfully. Payment ID: " + paymentId);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void viewPayments(Customer customer) {
        List<Payment> payments = customerController.viewPayments(customer);
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            for (Payment payment : payments) {
                System.out.println("================================");
                System.out.println("Payment ID: " + payment.getPaymentId());
                System.out.println("Policy ID: " + payment.getPolicyId());
                System.out.println("Amount: " + payment.getAmount());
                System.out.println("================================");
            }
        }
    }
}
