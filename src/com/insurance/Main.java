package com.insurance;

import com.insurance.model.Admin;
import com.insurance.model.Customer;
import com.insurance.model.Policy;
import com.insurance.model.User;
import com.insurance.view.AdminView;
import com.insurance.view.CustomerView;
import com.insurance.controller.AdminControllerImpl;
import com.insurance.controller.CustomerController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Updated users
        User admin = new Admin("admin", "admin@123");
        User customer1 = new Customer("jay", "Jay@123");
        User customer2 = new Customer("vani", "Vani@123");
        User customer3 = new Customer("sushil", "Sushil@123");

        User[] addUsers = {admin, customer1, customer2, customer3};

        AdminControllerImpl adminController = new AdminControllerImpl();
        CustomerController customerController = new CustomerController(adminController, adminController);
        AdminView adminView = new AdminView(adminController);
        CustomerView customerView = new CustomerView(customerController);

        // Initialize sample policies
        Policy policy1 = new Policy(1, "Health Insurance", "Health", "Full Body", 220);
        Policy policy2 = new Policy(2, "Car Insurance", "Vehicle", "Car", 150);
        Policy policy3 = new Policy(3, "Home Insurance", "Property", "Home", 300);
        adminController.addPolicy(policy1);
        adminController.addPolicy(policy2);
        adminController.addPolicy(policy3);

        boolean exitSystem = false;

        while (!exitSystem) {
            int attempts = 0;
            boolean loggedIn = false;

            while (attempts < 3 && !loggedIn) {
                System.out.println("Enter User Name:");
                String uname = scanner.next();

                System.out.println("Enter Password:");
                String pass = scanner.next();

                User loggedInUser = null;

                for (User u : addUsers) {
                    if (u.getUsername().equals(uname) && u.getPassword().equals(pass)) {
                        loggedInUser = u;
                        break;
                    }
                }

                if (loggedInUser instanceof Admin) {
                    adminView.displayAdminMenu((Admin) loggedInUser);
                    loggedIn = true;
                } else if (loggedInUser instanceof Customer) {
                    customerView.displayCustomerMenu((Customer) loggedInUser);
                    loggedIn = true;
                } else {
                    attempts++;
                    System.out.println("Invalid User. Attempts remaining: " + (3 - attempts));
                    if (attempts >= 3) {
                        System.out.println("Too many failed attempts. System is quitting. Please try rerunning the system.");
                        return;
                    }
                }
            }

            System.out.println("Do you want to log in again? (yes/no):");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                exitSystem = true;
            }
        }

        scanner.close();
    }
}
