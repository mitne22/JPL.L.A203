package fa.training.services;

import fa.training.entities.Customer;
import fa.training.utils.Constants;
import fa.training.utils.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = new OrderService();

    public List<Customer> createCustomer() {
        List<Customer> customers = new ArrayList<>();
        String choice;

        do {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();
            while (!Validator.isValidPhone(phone)) {
                System.out.print("Invalid. Re-enter phone: ");
                phone = scanner.nextLine();
            }

            System.out.print("Enter address: ");
            String address = scanner.nextLine();

            System.out.println("Enter order info:");
            List<fa.training.entities.Order> orders = orderService.inputOrders();

            customers.add(new Customer(name, phone, address, orders));

            System.out.print("Continue adding customers? (y/n): ");
            choice = scanner.nextLine();
        } while (!choice.equalsIgnoreCase("n"));

        return customers;
    }

    public String save(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.FILE_NAME))) {
            oos.writeObject(customers);
            return "Save successful.";
        } catch (IOException e) {
            return "Error saving: " + e.getMessage();
        }
    }

    public List<Customer> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.FILE_NAME))) {
            return (List<Customer>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void display(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer c : customers) {
                System.out.println(c);
            }
        }
    }

    public List<Customer> search(String phone) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : findAll()) {
            if (c.getPhone().equals(phone)) {
                result.add(c);
            }
        }
        return result;
    }

    public boolean remove(String phone) {
        List<Customer> all = findAll();
        boolean removed = all.removeIf(c -> c.getPhone().equals(phone));
        if (removed) {
            save(all);
        }
        return removed;
    }
}
