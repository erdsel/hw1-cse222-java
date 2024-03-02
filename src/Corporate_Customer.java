class Corporate_Customer extends Customer {
    private String company_name;

    // Parametre alan constructor
    public Corporate_Customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }

    // Parametresiz constructor
    public Corporate_Customer() {
        super(); // Customer sınıfının parametresiz constructor'ını çağırır
        this.company_name = ""; // Varsayılan bir şirket adı ile başlat
    }

    // Getter method for company_name
    public String getCompanyName() {
        return company_name;
    }

    // Setter method for company_name
    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    // Diğer gereken getter ve setter metodları...

    // Name için getter
    public String getName() {
        return name;
    }

    // Name için setter
    public void setName(String name) {
        this.name = name;
    }

    // Surname için getter
    public String getSurname() {
        return surname;
    }

    // Surname için setter
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Address için getter
    public String getAddress() {
        return address;
    }

    // Address için setter
    public void setAddress(String address) {
        this.address = address;
    }

    // Phone için getter
    public String getPhone() {
        return phone;
    }

    // Phone için setter
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // ID için getter
    public int getID() {
        return ID;
    }

    // ID için setter
    public void setID(int ID) {
        this.ID = ID;
    }

    // Operator_ID için getter
    public int getOperator_ID() {
        return operator_ID;
    }

    // Operator_ID için setter
    public void setOperator_ID(int operator_ID) {
        this.operator_ID = operator_ID;
    }

    // Method to print corporate customer details (already implemented above)

    @Override
    public void print_customer() {
        super.print_customer(); // Üst sınıfın metodunu çağırarak temel bilgileri yazdırır
        System.out.println("Company Name: " + getCompanyName()); // Şirket adını yazdırır
    }


    // Additional methods if needed...
}
