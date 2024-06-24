package com.insurance.view;

import com.insurance.model.Admin;
import com.insurance.model.Payment;
import com.insurance.model.Policy;
import com.insurance.controller.AdminControllerImpl;
import com.insurance.controller.PolicyController;
import com.insurance.controller.PaymentController;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private Scanner scanner = new Scanner(System.in);
    private PolicyController policyController;
    private PaymentController paymentController;

    public AdminView(AdminControllerImpl adminController) {
        this.policyController = adminController;
        this.paymentController = adminController;
    }

    public void displayAdminMenu(Admin admin) {
        while (true) {
            System.out.println("Admin Dashboard");
            System.out.println("1. Add Policy");
            System.out.println("2. View Policies");
            System.out.println("3. View Payments");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Payment");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addPolicy();
                    break;
                case 2:
                    viewPolicies();
                    break;
                case 3:
                    viewPayments();
                    break;
                case 4:
                    updatePolicy();
                    break;
                case 5:
                    deletePayment();
                    break;
                case 6:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addPolicy() {
        try {
            System.out.println("Enter Policy ID:");
            int policyId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter Policy Name:");
            String policyName = scanner.nextLine();

            System.out.println("Enter Category:");
            String category = scanner.nextLine();

            System.out.println("Enter Sub-Category:");
            String subCategory = scanner.nextLine();

            System.out.println("Enter Price:");
            double price = scanner.nextDouble();

            Policy policy = new Policy(policyId, policyName, category, subCategory, price);
            policyController.addPolicy(policy);

            System.out.println("Policy added: " + policy.getPolicyName());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void viewPolicies() {
        List<Policy> policies = policyController.getAllPolicies();
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

    private void viewPayments() {
        List<Payment> payments = paymentController.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        } else {
            for (Payment payment : payments) {
                System.out.println("================================");
                System.out.println("Payment ID: " + payment.getPaymentId());
                System.out.println("Policy ID: " + payment.getPolicyId());
                System.out.println("Username: " + payment.getUsername());
                System.out.println("Amount: " + payment.getAmount());
                System.out.println("================================");
            }
        }
    }

    private void updatePolicy() {
        List<Policy> policies = policyController.getAllPolicies();
        if (policies.isEmpty()) {
            System.out.println("No policies available to update.");
            return;
        }

        System.out.println("Select a policy to update by its ID:");
        for (Policy policy : policies) {
            System.out.println("================================");
            System.out.println("Policy ID: " + policy.getPolicyId());
            System.out.println("Name: " + policy.getPolicyName());
            System.out.println("Category: " + policy.getCategory());
            System.out.println("Sub-Category: " + policy.getSubCategory());
            System.out.println("Price: " + policy.getPrice());
            System.out.println("================================");
        }

        int policyId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Policy policy = policyController.getPolicy(policyId);
        if (policy == null) {
            System.out.println("Policy not found.");
            return;
        }

        try {
            System.out.println("Enter new Policy Name (leave blank to keep current):");
            String policyName = scanner.nextLine();
            if (!policyName.isEmpty()) {
                policy.setPolicyName(policyName);
            }

            System.out.println("Enter new Category (leave blank to keep current):");
            String category = scanner.nextLine();
            if (!category.isEmpty()) {
                policy.setCategory(category);
            }

            System.out.println("Enter new Sub-Category (leave blank to keep current):");
            String subCategory = scanner.nextLine();
            if (!subCategory.isEmpty()) {
                policy.setSubCategory(subCategory);
            }

            System.out.println("Enter new Price (leave blank to keep current):");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()) {
                double price = Double.parseDouble(priceInput);
                policy.setPrice(price);
            }

            policyController.updatePolicy(policy);

            System.out.println("Policy updated successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void deletePayment() {
        try {
            System.out.println("Enter Payment ID to delete:");
            int paymentId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Payment payment = paymentController.getPayment(paymentId);
            if (payment == null) {
                System.out.println("Payment ID not found.");
                return;
            }

            paymentController.deletePayment(paymentId);
            System.out.println("Payment deleted successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct values.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }
}
