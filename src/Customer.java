class Customer extends Person {
    protected Order[] orders; // Assuming a fixed size for simplicity, e.g., 10
    protected int operator_ID;

    private int numOrders; // To keep track of the number of orders added

    // Constructor
    // Parametresiz constructor
    public Customer() {
        super();
        this.orders = new Order[100]; // 100 boyutunda bir sipariş dizisi başlatılıyor.
        this.numOrders = 0;
    }

    public Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super();
        this.operator_ID = operator_ID;
        this.orders = new Order[100]; // 100 boyutunda bir sipariş dizisi başlatılıyor.
        this.numOrders = 0;
    }

    // Getter for orders
    public Order[] getOrders() {
        return orders;
    }

    // Setter for orders
    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    // Getter for operator_ID
    public int getOperator_ID() {
        return operator_ID;
    }

    // Setter for operator_ID
    public void setOperator_ID(int operator_ID) {
        this.operator_ID = operator_ID;
    }

    // Method to print customer details
    public void print_customer() {
        System.out.println("*** Customer Screen ***");
        System.out.println("Customer ID: " + ID);
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Operator ID: " + operator_ID);
        print_orders_for_customer();
    }

    // Method to print all orders' details


    // Method to define orders for this customer
    public void define_orders(Order[] orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (numOrders < orders.length) {
            orders[numOrders] = order;
            numOrders++;
        } else {
            System.out.println("Maximum number of orders reached for customer ID: " + this.ID);
        }
    }

    public void print_orders_for_customer() {
        System.out.println("Orders for customer ID: " + this.ID);
        for (int i = 0; i < numOrders; i++) {
            if (orders[i] != null) {
                System.out.println("Order #" + (i + 1) + " => " + orders[i].toString());
                System.out.println("----------------------------------------------------");
            }
        }
    }


}
