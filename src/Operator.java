// Operator class, extends the Person class
class Operator extends Person {
    int wage;
    private Customer[] customers;

    // Constructor
    public Operator(){
        super();

        this.name = this.name;
        this.surname = this.surname;
        this.address = this.address;
        this.phone = this.phone;
        this.ID = this.ID;
        this.wage = this.wage;
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

    }
    public void addCustomer(Customer customer) {
        for (int i = 0; i < this.customers.length; i++) {
            if (this.customers[i] == null) {
                this.customers[i] = customer;
                return; // Müşteriyi ekledikten sonra döngüden çık
            }
        }
        System.out.println("This operator's customer list is full."); // Müşteri listesi doluysa
    }


}
