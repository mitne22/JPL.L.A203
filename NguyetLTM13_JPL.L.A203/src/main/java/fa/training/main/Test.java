package fa.training.main;

import fa.training.entities.Customer;
import fa.training.services.CustomerService;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();

        while (true) {
            System.out.println("\nChoose function:");
            System.out.println("1. Add a new Customer");
            System.out.println("2. Show all Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    List<Customer> customers = customerService.createCustomer();
                    System.out.println(customerService.save(customers));
                    break;
                case 2:
                    System.out.println("-----LIST OF Customer------");
                    customerService.display(customerService.findAll());
                    break;
                case 3:
                    System.out.print("Enter phone to search: ");
                    String phone = scanner.nextLine();
                    List<Customer> found = customerService.search(phone);
                    customerService.display(found);
                    break;
                case 4:
                    System.out.print("Enter phone to remove: ");
                    phone = scanner.nextLine();
                    System.out.println(customerService.remove(phone) ? "Removed successfully!" : "Not found!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
