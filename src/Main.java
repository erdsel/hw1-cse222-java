import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Operator> operators = new HashMap<>();
        HashMap<Integer, Customer> customers = new HashMap<>();
        HashMap<Integer, Order> orders = new HashMap<>();

        // Dosya okuma
        try {
            File file = new File("content.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");

                switch (data[0]) {
                    case "order":
                        // Order verilerini işle ve orders map'ine ekle
                        break;
                    case "retail_customer":
                        // RetailCustomer verilerini işle ve customers map'ine ekle
                        break;
                    case "corporate_customer":
                        // CorporateCustomer verilerini işle ve customers map'ine ekle
                        break;
                    case "operator":
                        // Operator verilerini işle ve operators map'ine ekle
                        break;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı.");
            return;
        }

        // Kullanıcıdan ID girişi al
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Lütfen bir ID numarası giriniz:");
        int ID = inputScanner.nextInt();

        // ID'ye göre bilgileri yazdır
        if (operators.containsKey(ID)) {
            // Operator ve müşterilerinin bilgilerini yazdır
        } else if (customers.containsKey(ID)) {
            // Müşterinin bilgilerini yazdır
        } else {
            System.out.println("Girilen ID ile eşleşen bir operatör veya müşteri bulunamadı.");
        }

        inputScanner.close();
    }
}
