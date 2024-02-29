class Customer extends Person {
    protected Order[] orders; // Assuming a fixed size for simplicity, e.g., 10
    protected int operator_ID;

    // Constructor
    public Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
        this.operator_ID = operator_ID;
        this.orders = new Order[10]; // Initialize with some default capacity
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
        System.out.println("Customer ID: " + ID);
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Operator ID: " + operator_ID);
    }

    // Method to print all orders' details
    public void print_orders() {
        if (orders != null) {
            for (Order order : orders) {
                if (order != null) {
                    order.print_order();
                }
            }
        }
    }

    // Method to define orders for this customer
    public void define_orders(Order[] orders) {
        this.orders = orders;
    }
}
