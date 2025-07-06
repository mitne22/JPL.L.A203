package fa.training.services;

import fa.training.entities.Order;
import fa.training.utils.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final Scanner scanner = new Scanner(System.in);

    public List<Order> inputOrders() {
        List<Order> orders = new ArrayList<>();
        String choice;

        do {
            System.out.print("+ number: ");
            String number = scanner.nextLine();
            while (!Validator.isValidOrderNumber(number)) {
                System.out.print("Invalid. Re-enter 10-digit order number: ");
                number = scanner.nextLine();
            }

            Date date = null;
            while (true) {
                System.out.print("+ date (dd/MM/yyyy): ");
                String dateStr = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                try {
                    date = sdf.parse(dateStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format!");
                }
            }

            orders.add(new Order(number, date));

            System.out.print("Continue adding orders? (y/n): ");
            choice = scanner.nextLine();
        } while (!choice.equalsIgnoreCase("n"));

        return orders;
    }
}
