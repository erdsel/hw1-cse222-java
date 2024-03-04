import java.io.File;

import java.util.Scanner;

public class Main {
    private static final int MAX_OPERATORS = 100;
    private static final int MAX_CUSTOMERS = 100;
    private static final int MAX_ORDERS = 100;
    private static Operator[] operators = new Operator[MAX_OPERATORS];
    private static Customer[] customers = new Customer[MAX_CUSTOMERS];
    private static Order[] orders = new Order[MAX_ORDERS];
    private static int operatorCount = 0;
    private static int customerCount = 0;
    private static int orderCount = 0;

    public static void main(String[] args) {
        // Dosyadan verileri oku ve dizi yapılarını doldur
        readFileAndPopulateArrays();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen bir ID giriniz:");
        String input = scanner.nextLine(); // Read the input as a string

        try {
            // Attempt to parse the string to an integer
            int id = Integer.parseInt(input);
            // Additional integer checks can be done in the checkInteger method
            checkInteger(id);
            // ID'ye göre arama yap ve sonuçları yazdır
            printDetailsById(id);
        } catch (NumberFormatException e) {
            // This exception is thrown by parseInt if the string cannot be parsed to an integer
            System.out.println("Hatalı giriş: Lütfen geçerli bir tam sayı giriniz.");
            System.exit(1);
        } finally {
            scanner.close();
        }
    }


    private static void readFileAndPopulateArrays() {
        Scanner scanner = null;
        try {
            File file = new File("content.txt");
            scanner = new Scanner(file);

            // Dosya varlığını kontrol et
            if (!file.exists()) {
                System.out.println("Dosya bulunamadı, lütfen dosyanın doğru yerde olduğundan emin olun.");
                return; // Dosya yoksa metodun geri kalanını çalıştırmadan çık
            }
            int lineNumber = 1; // Keep track of the line number for error messages

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(";")) {
                    // System.out.println("Error at line " + lineNumber + ": Line doesn't have a semicolon.");
                    lineNumber++;
                    continue;
                }
                if (line.trim().isEmpty()) {
                    //System.out.println("Error at line " + lineNumber + ": Line is empty.");
                    lineNumber++;
                    continue;
                }

                String[] data = line.split(";");
                // Check for empty strings in data
                for (String field : data) {
                    if (field.isEmpty()) {
                        System.out.println("Error at line " + lineNumber + ": String is empty.");
                        lineNumber++;
                        continue;
                    }
                }

                // Define expected number of columns for each record type
                int expectedColumns = 0;
                if (data[0].equals("operator")) {
                    expectedColumns = 7;
                } else if (data[0].equals("retail_customer") || data[0].equals("corporate_customer")) {
                    expectedColumns = data[0].equals("corporate_customer") ? 8 : 7;
                } else if (data[0].equals("order")) {
                    expectedColumns = 6;
                }

// Check for extra or missing columns
                if (data.length > expectedColumns) {
                    System.out.println("Error at line " + lineNumber + ": Extra column.");
                } else if (data.length < expectedColumns) {
                    System.out.println("Error at line " + lineNumber + ": Missing column.");
                } else {
                    switch (data[0]) {
                        case "operator":
                            // Verileri ayrıştır
                            String name = data[1];
                            String surname = data[2];
                            String address = data[3];
                            String phone = data[4];
                            int ID = Integer.parseInt(data[5]);
                            int wage = Integer.parseInt(data[6]);

                            // Operator nesnesi oluştur
                            Operator operator = new Operator();
                            operator.setName(name);
                            operator.setSurname(surname);
                            operator.setAddress(address);
                            operator.setPhone(phone);
                            operator.setID(ID);
                            operator.setWage(wage);

                            operators[operatorCount++] = operator;
                            break;
                        case "retail_customer":
                            int customerIDRetail = Integer.parseInt(data[5]);
                            int operatorIDRetail = Integer.parseInt(data[6]);
                            String retailcustomerName = data[1];
                            String retailcustomerSurname = data[2];
                            String retailcustomerAddress = data[3];
                            String retailcustomerPhone = data[4];

                            // Retail_Customer nesnesini parametreler ile oluşturur
                            Retail_Customer retailCustomer = new Retail_Customer();

                            // Verileri setter metotları ile nesneye aktarır
                            retailCustomer.setName(retailcustomerName);
                            retailCustomer.setSurname(retailcustomerSurname);
                            retailCustomer.setAddress(retailcustomerAddress);
                            retailCustomer.setPhone(retailcustomerPhone);
                            retailCustomer.setID(customerIDRetail);
                            retailCustomer.setOperator_ID(operatorIDRetail);

                            customers[customerCount++] = retailCustomer;
                            break;
                        case "corporate_customer":
                            int customerIDCorporate = Integer.parseInt(data[5]);
                            int operatorIDCorporate = Integer.parseInt(data[6]);
                            String corporateCustomerName = data[1];
                            String corporateCustomerSurname = data[2];
                            String corporateCustomerAddress = data[3];
                            String corporateCustomerPhone = data[4];
                            String companyName = data[7];

                            // Corporate_Customer nesnesini parametresiz constructor ile oluştur
                            Corporate_Customer corporateCustomer = new Corporate_Customer();

                            // Verileri setter metotları ile nesneye aktar
                            corporateCustomer.setName(corporateCustomerName);
                            corporateCustomer.setSurname(corporateCustomerSurname);
                            corporateCustomer.setAddress(corporateCustomerAddress);
                            corporateCustomer.setPhone(corporateCustomerPhone);
                            corporateCustomer.setID(customerIDCorporate);
                            corporateCustomer.setOperator_ID(operatorIDCorporate);
                            corporateCustomer.setCompanyName(companyName);

                            customers[customerCount++] = corporateCustomer;
                            break;
                        case "order":
                            // order verilerini işleme
                            String productName = data[1];
                            int count = Integer.parseInt(data[2]);
                            int total_price = Integer.parseInt(data[3]);
                            int status = Integer.parseInt(data[4]);
                            int customerID = Integer.parseInt(data[5]);

                            // Order nesnesini parametresiz constructor ile oluştur
                            Order order = new Order();

                            // Verileri setter metotları ile nesneye aktar
                            order.setProduct_name(productName);
                            order.setCount(count);
                            order.setTotal_price(total_price);
                            order.setStatus(status); // Bu metot status int değerini alıp içeride string'e çevirebilir
                            order.setCustomer_ID(customerID);
                            orders[orderCount++] = order;
                            break;
                        default:
                            // Handle unexpected data type
                            break;
                    }
                }
                lineNumber++;


            }
            scanner.close();
        } catch (Exception e) {
            // Catch all other exceptions here
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // Link customers to their operators
        for (int i = 0; i < customerCount; i++) {
            int operatorId = customers[i].getOperator_ID();
            for (int j = 0; j < operatorCount; j++) {
                if (operators[j].getID() == operatorId) {
                    operators[j].addCustomer(customers[i]);
                    break;
                }
            }
        }

        // Link orders to their customers
        for (int i = 0; i < orderCount; i++) {
            int customerId = orders[i].getCustomer_ID();
            for (int j = 0; j < customerCount; j++) {
                if (customers[j].getID() == customerId) {
                    customers[j].addOrder(orders[i]);
                    break;
                }
            }
        }
    }

    private static void printDetailsById(int id) {
        // Operatörler üzerinde arama yap
        for (Operator operator : operators) {
            if (operator != null && operator.getID() == id) {
                operator.print_operator(); // Operatör bilgilerini yazdır
                operator.print_customers(); // Operatöre ait müşterilerin bilgilerini yazdır
                return; // İşlem tamamlandı, metottan çık
            }
        }

        // Müşteriler üzerinde arama yap
        for (Customer customer : customers) {
            if (customer != null && customer.getID() == id) {
                customer.print_customer(); // Müşteri bilgilerini yazdır
                return; // İşlem tamamlandı, metottan çık
            }
        }

        // Eğer bu ID'ye ait bir operatör veya müşteri bulunamazsa
        System.out.println("Bu ID'ye sahip bir operatör veya müşteri bulunamadı.");
    }

    private static boolean isKnownIdentifier(String identifier) {
        // Check against known identifiers
        return identifier.equals("operator") || identifier.equals("retail_customer")
                || identifier.equals("corporate_customer") || identifier.equals("order");
    }

    public static void checkInteger(int value) {

        try {


            if (value <= 0) {
                System.out.println("Invalid: Integer is not positive.");
                System.exit(1); // Terminate the program
            }


        } catch (NumberFormatException e) {
            System.out.println("Invalid: Integer is not convertible.");
            System.exit(1); // Terminate the program
        }


        if (!checkIfIdExists(value)) {
            System.out.println("No operator/customer was found with ID "+ value + ". Please try again.");
            System.exit(1); // Terminate the program
        }


        if (isIdValid(value)) {
            System.out.println("Invalid: ID read from the console is not valid.");
            System.exit(1); // Terminate the program
        }


    }
    public static boolean checkIfIdExists(int id) {
        // Check if ID exists in operators array
        for (Operator operator : operators) {
            if (operator != null && operator.getID() == id) {
                return true; // ID exists for an operator
            }
        }
        // Check if ID exists in customers array
        for (Customer customer : customers) {
            if (customer != null && customer.getID() == id) {
                return true; // ID exists for a customer
            }
        }
        // Check if ID exists in orders array
        for (Order order : orders) {
            if (order != null && order.getCustomer_ID() == id) {
                return true; // ID exists for an order
            }
        }
        return false; // ID does not exist
    }
    public static boolean isIdValid(int id) {
        // ID should be within a specific range
        final int MIN_ID_VALUE = 1; //  minimum value
        final int MAX_ID_VALUE = 100; // maximum value


        if (id < MIN_ID_VALUE || id > MAX_ID_VALUE) {
            return false; // ID is not within the valid range
        }
        return true; // ID is valid
    }
}
