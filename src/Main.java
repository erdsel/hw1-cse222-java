import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Operator> operators = new HashMap<>();
        HashMap<Integer, Customer> customers = new HashMap<>();
        HashMap<Integer, Order> orders = new HashMap<>();

        // Dosyadan verileri oku ve HashMap'leri doldur
        readFileAndPopulateMaps(operators, customers, orders);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen bir ID giriniz:");
        int id = scanner.nextInt();

        if (operators.containsKey(id)) {
            Operator operator = operators.get(id);
            operator.print_operator(); // Operatör bilgilerini yazdır
            operator.print_customers(); // Operatöre bağlı müşterilerin bilgilerini yazdır
        } else if (customers.containsKey(id)) {
            Customer customer = customers.get(id);
            customer.print_customer(); // Müşteri bilgilerini yazdır
        } else {
            System.out.println("Bu ID'ye sahip bir operatör veya müşteri bulunamadı.");
        }

        scanner.close();
    }

    private static void readFileAndPopulateMaps(HashMap<Integer,Operator>operators,HashMap<Integer, Customer> customers,
                                                HashMap<Integer, Order> orders ) {
        try {
            File file= new File("content.txt");
            Scanner fileScanner = new Scanner(file);
            while (
                    fileScanner.hasNextLine()
            ){
                String line= fileScanner.nextLine();
                String[] data= line.split(";");

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

                        // Operator'ün müşterilerini tanımla (bu aşama daha sonra yapılacak)
                        // Önce tüm operatörler ve müşteriler okunmalı
                        // Sonra müşterilerin operator_ID'lerine göre operatörlere atanmalı

                        // Operatörü HashMap'e ekle
                        operators.put(ID, operator);


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

                        // Müşteriyi HashMap'e ekler
                        customers.put(customerIDRetail, retailCustomer);

                        // Müşteri bilgilerini yazdırır
                      //  retailCustomer.print_customer();

                        break;


                    case "corporate_customer":
                        //corporate customer verilerini işleme
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

                        // Müşteriyi customers HashMap'ine ekler
                        customers.put(customerIDCorporate, corporateCustomer);

                        // Müşteri bilgilerini yazdırır
                    //    corporateCustomer.print_customer();

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

                        // Siparişi orders HashMap'ine ekler
                        orders.put(customerID, order);

                        // Sipariş bilgilerini yazdırır (toString metodu bu formatta string döndürmelidir)
                       // System.out.println(order);

                        break;

                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Customer customer : customers.values()) {
            // Her müşteri için, müşterinin operatörünü operators HashMap'inden al
            Operator operator = operators.get(customer.getOperator_ID());
            if (operator != null) {
                // Eğer bu operatör varsa, müşteriyi operatörün müşteri listesine ekle
                for (int i = 0; i < operator.getCustomers().length; i++) {
                    // Operatörün müşteri dizisinde boş bir yer ara
                    if (operator.getCustomers()[i] == null) {
                        // Boş yer bulunca, o yere müşteriyi ekle ve döngüden çık
                        operator.getCustomers()[i] = customer;
                        break;
                    }
                }
            }
        }

// Şimdi siparişleri müşterilere atama işlemi yap
        for (Order order : orders.values()) {

            // Siparişin müşteri ID'sini kullanarak ilgili müşteriyi customers HashMap'inden al
            Customer customer = customers.get(order.getCustomer_ID());
            if (customer != null) {
                // Eğer bu müşteri varsa, siparişi müşterinin sipariş listesine ekle
                // Customer sınıfında sipariş eklemek için bir metod varsayılıyor (örneğin, addOrder)
                customer.addOrder(order);
            }
        }



    }

}