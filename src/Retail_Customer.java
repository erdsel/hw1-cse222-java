class Retail_Customer extends Customer {
    // Retail-specific attributes can be added here

    // Parametre alan constructor
    public Retail_Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID, operator_ID);
    }

    // Parametresiz constructor
    public Retail_Customer() {
        super(); // Customer sınıfının parametresiz constructor'ını çağırır
    }

    // Getters ve Setters (Customer sınıfından miras alınan özellikler için)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOperator_ID() {
        return operator_ID;
    }

    public void setOperator_ID(int operator_ID) {
        this.operator_ID = operator_ID;
    }
}
