class Order {
    public String product_name;
    public int count;
    public int total_price;
    public int status;
    public int customer_ID;

    // Getters
    public String getProduct_name() {
        return product_name;
    }

    public int getCount() {
        return count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public int getStatus() {
        return status;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    // Setters
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public void print_order() {
        System.out.println("Product Name: " + product_name);
        System.out.println("Count: " + count);
        System.out.println("Total Price: " + total_price);
        System.out.println("Status: " + status);
        System.out.println("Customer ID: " + customer_ID);
    }

    @Override
    public String toString() {
        return "Product name: " + this.getProduct_name() +
                " - Count: " + this.getCount() +
                " - Total price: " + this.getTotal_price() +
                " - Status: " + getStatusString(this.status);
    }

    // Status int değerini karşılık gelen String değere dönüştüren yardımcı metod
    private String getStatusString(int status) {
        switch (status) {
            case 0: return "Initialized";
            case 1: return "Processing";
            case 2: return "Completed";
            case 3: return "Cancelled";
            default: return "Unknown";
        }
    }

    // Constructor can be added as needed
}
