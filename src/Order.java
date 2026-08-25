import java.util.ArrayList;

public class Order {

    private int orderId;
    private String customerName;
    private ArrayList<OrderItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(MenuItem item, int quantity) {

        if (status == OrderStatus.COMPLETED ||
                status == OrderStatus.CANCELLED) {

            System.out.println("Cannot modify a completed or cancelled order.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        items.add(new OrderItem(item, quantity));
        calculateTotal();

        System.out.println("Item added to order.");
    }

    public void removeItem(int menuItemId) {

        if (status == OrderStatus.COMPLETED ||
                status == OrderStatus.CANCELLED) {

            System.out.println("Cannot modify a completed or cancelled order.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getItem().getId() == menuItemId) {

                items.remove(i);
                calculateTotal();

                System.out.println("Item removed from order.");
                return;
            }
        }

        System.out.println("Item not found in order.");
    }

    public void calculateTotal() {

        total = 0.0;

        for (OrderItem orderItem : items) {
            total += orderItem.calculateSubtotal();
        }
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void displayOrder() {

        System.out.println("\n==============================");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Status: " + status);
        System.out.println("------------------------------");

        if (items.isEmpty()) {
            System.out.println("No items in this order.");
        } else {

            for (OrderItem orderItem : items) {
                System.out.println(orderItem);
            }
        }

        System.out.println("------------------------------");
        System.out.printf("Total: %.2f%n", total);
        System.out.println("==============================");
    }
}