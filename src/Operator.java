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
        System.out.println("*** Operator Screen ***");
        System.out.println("----------------------------");
        System.out.println("Operator ID: " + ID);
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Wage: " + wage);
        System.out.println("----------------------------");
    }

    public void print_customers() {
        int customerNumber = 1; // Müşteri sıra numarası için sayaç
        if (this.customers == null || this.customers.length == 0 || this.customers[0] == null) {
            System.out.println("This operator doesn't have any customer.");
        }
        for (Customer customer : customers) {
            if (customer != null) {
                // Müşteri tipini kontrol eder ve yazdırır
                if (customer instanceof Retail_Customer) {
                    System.out.println("Customer #" + customerNumber + " (a retail customer):");
                } else if (customer instanceof Corporate_Customer) {
                    System.out.println("Customer #" + customerNumber + " (a corporate customer):");
                }
                // Müşteri bilgilerini yazdırır
                customer.print_customer();
                customerNumber++; // Sıra numarasını artırır
            }
        }
    }

    public void define_customers(Customer[] customers) {
        this.customers = customers;
        // Implement method to define customers for this operator
    }


}
