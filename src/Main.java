import java.io.File;
import java.io.FileNotFoundException;
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
        int id = scanner.nextInt();

        // ID'ye göre arama yap ve sonuçları yazdır
        printDetailsById(id);

        scanner.close();
    }

    private static void readFileAndPopulateArrays() {
        try {
            File file = new File("content.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
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
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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


    // Diğer yardımcı metodlarınız burada olacak
}
