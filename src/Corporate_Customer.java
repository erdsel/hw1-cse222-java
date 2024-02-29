class Corporate_Customer extends Customer {
    private String company_name;

    // Constructor that calls the superclass constructor and initializes company_name
    public Corporate_Customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }

    // Getter method for company_name
    public String getCompanyName() {
        return company_name;
    }

    // Setter method for company_name
    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    // Method to print corporate customer details
    @Override
    public void print_customer() {
        super.print_customer(); // Call the method from the superclass
        System.out.println("Company Name: " + company_name);
    }

    // You can override other methods from the Customer class if necessary
    // For example, if the way orders are printed is different for corporate customers
    // you can override the print_orders() method here
}
