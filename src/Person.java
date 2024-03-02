// Base class for all person entities in the system
class Person {
    protected String name;
    protected String surname;
    protected String address;
    protected String phone;
    protected int ID;

    // Constructor
    public Person() {
        this.name = this.name;
        this.surname = this.surname;
        this.address = this.address;
        this.phone = this.phone;
        this.ID = this.ID;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getID() {
        return ID;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
