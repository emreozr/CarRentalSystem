package com.carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final CarInventory inventory = new CarInventory();
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Rental> rentals = new ArrayList<>();
    private static final List<Payment> payments = new ArrayList<>();

    private static int nextCustomerId = 1;
    private static int nextRentalId = 1;
    private static int nextPaymentId = 1;

    public static void main(String[] args) {

        // 50 araç seed (Gas + Electric + Luxury)
        inventory.addCars(DataSeeder.seedCars());

        while (true) {
            printMenu();
            int choice = readInt("Seçim: ");

            try {
                switch (choice) {
                    case 1 -> inventory.listAvailableCars();
                    case 2 -> handleRent();
                    case 3 -> handleReturn();
                    case 4 -> inventory.listAllCars();
                    case 5 -> listRentals();
                    case 6 -> listPayments();
                    case 7 -> handleFilteringMenu();
                    case 8 -> handleAddCar();
                    case 9 -> handleRemoveCar();
                    case 10 -> printReceipt();
                    case 0 -> exit();
                    default -> System.out.println("Geçersiz seçim!");
                }
            } catch (RuntimeException e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    // ================= MENU =================

    private static void printMenu() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("        ARAÇ KİRALAMA SİSTEMİ          ");
        System.out.println("======================================");
        System.out.println("[1]  Müsait araçları listele");
        System.out.println("[2]  Araç kirala");
        System.out.println("[3]  Araç iade et (Rental ID)");
        System.out.println("[4]  Tüm araçları listele");
        System.out.println("[5]  Kiralamaları listele");
        System.out.println("[6]  Ödemeleri listele");
        System.out.println("--------------------------------------");
        System.out.println("[7]  Filtreleme menüsü");
        System.out.println("[8]  Araç ekle");
        System.out.println("[9]  Araç sil (ID)");
        System.out.println("--------------------------------------");
        System.out.println("[10] Receipt / Fiş yazdır");
        System.out.println("[0]  Çıkış");
        System.out.println("======================================");
    }


    // ================= INPUT =================

    private static int readInt(String label) {
        System.out.print(label);
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Geçerli bir sayı giriniz: ");
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private static int readPositiveInt(String label) {
        int val;
        do {
            val = readInt(label);
            if (val <= 0) System.out.println("Değer 1 veya daha büyük olmalı.");
        } while (val <= 0);
        return val;
    }

    private static String readLine(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static String readPhone(String label) {
        String phone;
        do {
            phone = readLine(label);
            if (!phone.matches("\\d{10,11}")) {
                System.out.println("Telefon sadece rakam ve 10-11 haneli olmalı.");
            }
        } while (!phone.matches("\\d{10,11}"));
        return phone;
    }

    private static PaymentMethod readPaymentMethod(String label) {
        while (true) {
            try {
                return PaymentMethod.valueOf(readLine(label).toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Geçersiz yöntem (CASH / CARD / TRANSFER / MOBILE_PAY)");
            }
        }
    }

    private static FuelType readFuelType(String label) {
        while (true) {
            try {
                return FuelType.valueOf(readLine(label).toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Geçersiz yakıt türü (BENZIN / DIZEL / LPG)");
            }
        }
    }

    // ================= RENT =================

    private static void handleRent() {
        int carId = readInt("Araç ID: ");
        Car car = inventory.findCarById(carId);

        if (car == null) {
            System.out.println("Araç bulunamadı.");
            return;
        }
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Araç şu an kirada.");
        }

        String name = readLine("Müşteri adı: ");
        String phone = readPhone("Telefon: ");

        Customer customer = new Customer(nextCustomerId++, name, phone);
        customers.add(customer);

        int days = readPositiveInt("Kaç gün kiralanacak? ");

        Rental rental = new Rental(nextRentalId++, car, customer, days);
        rentals.add(rental);

        PaymentMethod method =
                readPaymentMethod("Ödeme yöntemi (CASH / CARD / TRANSFER / MOBILE_PAY): ");

        Payment payment = new Payment(
                nextPaymentId++,
                rental.getRentalId(),
                rental.getTotalFee(),
                method
        );
        payments.add(payment);

        System.out.println("Kiralama başarılı:");
        System.out.println(rental);
        System.out.println("Ödeme alındı:");
        System.out.println(payment);
    }

    private static void handleReturn() {
        int rid = readInt("İade edilecek Rental ID: ");
        for (Rental r : rentals) {
            if (r.getRentalId() == rid) {
                r.closeRental();
                System.out.println("Araç iade edildi:");
                System.out.println(r);
                return;
            }
        }
        System.out.println("Rental bulunamadı.");
    }

    // ================= LIST =================

    private static void listRentals() {
        if (rentals.isEmpty()) {
            System.out.println("Kiralama yok.");
            return;
        }
        rentals.forEach(System.out::println);
    }

    private static void listPayments() {
        if (payments.isEmpty()) {
            System.out.println("Ödeme yok.");
            return;
        }
        payments.forEach(System.out::println);
    }

    // ================= FILTER =================

    private static void handleFilteringMenu() {
        System.out.println("\n--- FİLTRELEME ---");
        System.out.println("1 - Markaya göre");
        System.out.println("2 - Yakıt türüne göre");
        System.out.println("3 - GasCar");
        System.out.println("4 - ElectricCar");
        System.out.println("0 - Geri dön");

        int c = readInt("Seçim: ");

        switch (c) {
            case 1 -> printCars(inventory.filterAvailableByBrand(readLine("Marka: ")));
            case 2 -> printCars(inventory.filterAvailableByFuelType(readFuelType("Yakıt: ")));
            case 3 -> printCars(inventory.filterAvailableGasCars());
            case 4 -> printCars(inventory.filterAvailableElectricCars());
            case 0 -> { return; }
            default -> System.out.println("Geçersiz seçim!");
        }
    }

    private static void printCars(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("Sonuç yok.");
            return;
        }
        cars.forEach(System.out::println);
    }

    // ================= INVENTORY =================

    private static void handleAddCar() {
        System.out.println("\n--- ARAÇ EKLE ---");
        System.out.println("1 - GasCar");
        System.out.println("2 - ElectricCar");
        System.out.println("3 - LuxuryCar");

        int t = readInt("Tür: ");
        int id = readInt("ID: ");
        String brand = readLine("Marka: ");
        String model = readLine("Model: ");
        double dailyRate = readPositiveInt("Günlük ücret: ");

        Car car;

        if (t == 1) {
            car = new GasCar(id, brand, model, dailyRate, readFuelType("Yakıt: "));
        } else if (t == 2) {
            car = new ElectricCar(id, brand, model, dailyRate, readPositiveInt("Menzil: "));
        } else if (t == 3) {
            car = new LuxuryCar(id, brand, model, dailyRate);
        } else {
            System.out.println("Geçersiz tür.");
            return;
        }

        inventory.addCar(car);
        System.out.println("Araç eklendi: " + car);
    }

    private static void handleRemoveCar() {
        int id = readInt("Silinecek araç ID: ");
        System.out.println(
                inventory.removeCar(id) ? "Araç silindi." : "Araç bulunamadı."
        );
    }

    // ================= RECEIPT =================

    private static void printReceipt() {
        int rid = readInt("Rental ID: ");

        Rental rental = null;
        for (Rental r : rentals) {
            if (r.getRentalId() == rid) {
                rental = r;
                break;
            }
        }

        if (rental == null) {
            System.out.println("Rental bulunamadı.");
            return;
        }

        Payment payment = null;
        for (Payment p : payments) {
            if (p.getRentalId() == rid) {
                payment = p;
                break;
            }
        }

        System.out.println("\n===== RECEIPT =====");
        System.out.println(rental);
        if (payment != null) System.out.println(payment);
        else System.out.println("Ödeme bulunamadı.");
        System.out.println("===================");
    }

    // ================= EXIT =================

    private static void exit() {
        System.out.println("Çıkılıyor...");
        sc.close();
        System.exit(0);
    }
}
