// RetailCustomer class, extends the Customer class
class Retail_Customer extends Customer {
    // Retail-specific attributes can be added here
    public Retail_Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID, operator_ID);
    }

    // Constructor, getters and setters can be added as needed
}