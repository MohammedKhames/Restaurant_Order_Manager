import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Restaurant {

    private ArrayList<MenuItem> menu;
    private LinkedList<Order> kitchenQueue;
    private HashMap<Integer, Order> orders;
    private LinkedHashMap<Integer, Order> completedOrders;

    public Restaurant() {

        menu = new ArrayList<>();
        kitchenQueue = new LinkedList<>();
        orders = new HashMap<>();
        completedOrders = new LinkedHashMap<>();
    }


    // Menu Operations
    public void addMenuItem(MenuItem item) {

        if (findMenuItem(item.getId()) != null) {
            System.out.println("Menu item ID already exists.");
            return;
        }

        menu.add(item);

        System.out.println("Menu item added successfully.");
    }

    public void removeMenuItem(int id) {

        MenuItem item = findMenuItem(id);

        if (item == null) {
            System.out.println("Menu item not found.");
            return;
        }

        menu.remove(item);

        System.out.println("Menu item removed successfully.");
    }

    public void displayMenu() {

        System.out.println("\n========== MENU ==========");

        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }

        for (MenuItem item : menu) {
            System.out.println(item);
        }

        System.out.println("==========================");
    }

    public MenuItem findMenuItem(int id) {

        for (MenuItem item : menu) {

            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public void searchMenuItem(int id) {

        MenuItem item = findMenuItem(id);

        if (item == null) {
            System.out.println("Menu item not found.");
        } else {
            System.out.println(item);
        }
    }


    // Order Operations
    public void createOrder(int orderId, String customerName) {

        if (orders.containsKey(orderId)) {
            System.out.println("Order ID already exists.");
            return;
        }

        Order order = new Order(orderId, customerName);

        orders.put(orderId, order);

        System.out.println("Order created successfully.");
    }

    public Order findOrder(int orderId) {
        return orders.get(orderId);
    }

    public void addItemToOrder(int orderId, int menuItemId, int quantity) {

        Order order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        MenuItem item = findMenuItem(menuItemId);

        if (item == null) {
            System.out.println("Menu item not found.");
            return;
        }

        order.addItem(item, quantity);
    }

    public void removeItemFromOrder(int orderId, int menuItemId) {

        Order order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        order.removeItem(menuItemId);
    }

    public void displayOrder(int orderId) {

        Order order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        order.displayOrder();
    }

    // =========================
    // Kitchen Queue
    public void addOrderToKitchenQueue(int orderId) {

        Order order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.getStatus() != OrderStatus.PENDING) {

            System.out.println("Only PENDING orders can be added to the kitchen queue.");

            return;
        }

        if (kitchenQueue.contains(order)) {

            System.out.println("Order is already in the kitchen queue.");

            return;
        }

        kitchenQueue.addLast(order);

        order.updateStatus(OrderStatus.IN_KITCHEN);

        System.out.println("Order added to kitchen queue.");
    }

    public void processNextOrder() {

        if (kitchenQueue.isEmpty()) {

            System.out.println("Kitchen queue is empty.");

            return;
        }

        Order order = kitchenQueue.removeFirst();

        if (order.getStatus() == OrderStatus.COMPLETED ||
                order.getStatus() == OrderStatus.CANCELLED) {

            System.out.println("Order cannot be processed.");

            return;
        }

        order.updateStatus(OrderStatus.COMPLETED);

        completedOrders.put(order.getOrderId(), order);

        System.out.println("Order #" + order.getOrderId() + " has been completed.");
    }


    // Search / Status
    public void searchOrder(int orderId) {

        Order order = orders.get(orderId);

        if (order == null) {

            System.out.println("Order not found.");

            return;
        }

        order.displayOrder();
    }

    public void checkOrderStatus(int orderId) {

        Order order = orders.get(orderId);

        if (order == null) {

            System.out.println("Order not found.");

            return;
        }

        System.out.println("Order #" + orderId + " Status: " + order.getStatus());
    }


    // Completed Orders

    public void displayCompletedOrders() {

        System.out.println("\n===== COMPLETED ORDERS =====");

        if (completedOrders.isEmpty()) {

            System.out.println("No completed orders.");

            return;
        }

        for (Order order : completedOrders.values()) {

            order.displayOrder();
        }
    }
}