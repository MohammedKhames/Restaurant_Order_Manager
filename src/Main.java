import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Restaurant restaurant = new Restaurant();

    public static void main(String[] args) {

        loadSampleMenu();

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addMenuItem();
                    break;

                case 2:
                    removeMenuItem();
                    break;

                case 3:
                    restaurant.displayMenu();
                    break;

                case 4:
                    searchMenuItem();
                    break;

                case 5:
                    createOrder();
                    break;

                case 6:
                    addItemToOrder();
                    break;

                case 7:
                    removeItemFromOrder();
                    break;

                case 8:
                    displayOrder();
                    break;

                case 9:
                    addOrderToKitchen();
                    break;

                case 10:
                    restaurant.processNextOrder();
                    break;

                case 11:
                    searchOrder();
                    break;

                case 12:
                    checkOrderStatus();
                    break;

                case 13:
                    restaurant.displayCompletedOrders();
                    break;

                case 14:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }


    // Main Menu
    private static void displayMainMenu() {

        System.out.println("\n");
        System.out.println("==================================");
        System.out.println("   Restraunat Order Manager");
        System.out.println("==================================");

        System.out.println("01.  Add Menu Item");
        System.out.println("02.  Remove Menu Item");
        System.out.println("03.  Display Menu");
        System.out.println("04.  Search Menu Item");
        System.out.println("05.  Create Order");
        System.out.println("06.  Add Item to Order");
        System.out.println("07.  Remove Item from Order");
        System.out.println("08.  Display Order");
        System.out.println("09.  Add Order to Kitchen Queue");
        System.out.println("10.  Process Next Order");
        System.out.println("11.  Search Order");
        System.out.println("12.  Check Order Status");
        System.out.println("13.  Display Completed Orders");
        System.out.println("14.  Exit");

        System.out.println("==================================");
    }


    // Menu items
    private static void addMenuItem() {

        int id = readInt("Enter item ID: ");

        String name = readString("Enter item name: ");

        double price = readDouble("Enter item price: ");

        String category = readString("Enter item category: ");

        MenuItem item = new MenuItem(id, name, price, category);

        restaurant.addMenuItem(item);
    }

    private static void removeMenuItem() {

        int id = readInt("Enter menu item ID: ");

        restaurant.removeMenuItem(id);
    }

    private static void searchMenuItem() {

        int id = readInt("Enter menu item ID: ");

        restaurant.searchMenuItem(id);
    }


    // Orders
    private static void createOrder() {

        int orderId = readInt("Enter order ID: ");

        String customerName = readString("Enter customer name: ");

        restaurant.createOrder(orderId, customerName);
    }

    private static void addItemToOrder() {

        int orderId = readInt("Enter order ID: ");

        int menuItemId = readInt("Enter menu item ID: ");

        int quantity = readInt("Enter quantity: ");

        restaurant.addItemToOrder(
                orderId,
                menuItemId,
                quantity
        );
    }

    private static void removeItemFromOrder() {

        int orderId = readInt("Enter order ID: ");

        int menuItemId = readInt("Enter menu item ID: ");

        restaurant.removeItemFromOrder(orderId, menuItemId);
    }

    private static void displayOrder() {

        int orderId = readInt("Enter order ID: ");

        restaurant.displayOrder(orderId);
    }


    // Kitchen
    private static void addOrderToKitchen() {

        int orderId = readInt("Enter order ID: ");

        restaurant.addOrderToKitchenQueue(orderId);
    }


    // Search
    private static void searchOrder() {

        int orderId = readInt("Enter order ID: ");

        restaurant.searchOrder(orderId);
    }

    private static void checkOrderStatus() {

        int orderId = readInt("Enter order ID: ");

        restaurant.checkOrderStatus(orderId);
    }


    // Sample data
    private static void loadSampleMenu() {

        restaurant.addMenuItem(
                new MenuItem(
                        1,
                        "Burger",
                        150,
                        "Main Course"
                )
        );

        restaurant.addMenuItem(
                new MenuItem(
                        2,
                        "Pizza",
                        200,
                        "Main Course"
                )
        );

        restaurant.addMenuItem(
                new MenuItem(
                        3,
                        "Pasta",
                        180,
                        "Main Course"
                )
        );

        restaurant.addMenuItem(
                new MenuItem(
                        4,
                        "Cola",
                        40,
                        "Drinks"
                )
        );
    }


    // Input methods


    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}