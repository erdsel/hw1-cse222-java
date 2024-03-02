// Operator class, extends the Person class
class Operator extends Person {
    int wage;
    private Customer[] customers; // Assuming a fixed size for simplicity

    // Constructor
    public Operator(){
        super();

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
        this.wage = wage;
        // Assuming the fixed size for customers array is defined somewhere
        this.customers = new Customer[100];

    }

    // wage field's getter
    public  int getWage(){
        return wage;
    }

    // wage field's setter
    public void setWage(int wage) {
        this.wage = wage;
    }
    // customers array's getter
    public Customer[] getCustomers() {
        return customers;
    }
    public void setCustomers(Customer[] customers){
        this.customers = customers;
    }

    // Implement method to print operator details
    public void print_operator() {
        System.out.println("Operator ID: " + ID);
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Wage: " + wage);
    }

    public void print_customers() {
        for(Customer customer: customers){
            if(customer != null){
                customer.print_customer();;
            }
        }
    }

    public void define_customers(Customer[] customers) {
        this.customers = customers;
        // Implement method to define customers for this operator
    }

    // Constructor, getters and setters can be added as needed
}
