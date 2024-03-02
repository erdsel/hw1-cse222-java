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
                    // Diğer durumlar...
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}