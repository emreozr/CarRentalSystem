package com.carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based car rental application.
 * Handles user interaction and system workflow.
 */

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

        inventory.addCar(new GasCar(1, "Toyota", "Corolla", 800, FuelType.BENZIN));
        inventory.addCar(new ElectricCar(2, "Tesla", "Model 3", 1200, 500));

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
                    case 0 -> exit();
                    default -> System.out.println("Geçersiz seçim!");
                }
            } catch (RuntimeException e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    // ---------- MENU ----------

    private static void printMenu() {
        System.out.println("\n=== ARAÇ KİRALAMA SİSTEMİ ===");
        System.out.println("1 - Müsait araçları listele");
        System.out.println("2 - Araç kirala");
        System.out.println("3 - Araç iade et (Rental ID)");
        System.out.println("4 - Tüm araçları listele");
        System.out.println("5 - Kiralamaları listele");
        System.out.println("6 - Ödemeleri listele");
        System.out.println("0 - Çıkış");
    }

    // ---------- INPUT HELPERS ----------

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
                System.out.println("Geçersiz yöntem. (CASH / CARD)");
            }
        }
    }

    // ---------- OPERATIONS ----------

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
        String phone = readPhone("Telefon (10-11 hane): ");

        Customer customer = new Customer(nextCustomerId++, name, phone);
        customers.add(customer);

        int days = readPositiveInt("Kaç gün kiralanacak? ");

        Rental rental = new Rental(nextRentalId++, car, customer, days);
        rentals.add(rental);

        PaymentMethod method = readPaymentMethod("Ödeme yöntemi (CASH / CARD): ");

        Payment payment = new Payment(
                nextPaymentId++,
                rental.getRentalId(),
                rental.getTotalFee(),
                method
        );
        payments.add(payment);

        System.out.println("Kiralama oluşturuldu:");
        System.out.println(rental);
        System.out.println("Ödeme alındı:");
        System.out.println(payment);
    }

    private static void handleReturn() {
        int rid = readInt("İade edilecek Rental ID: ");
        for (Rental r : rentals) {
            if (r.getRentalId() == rid) {
                r.closeRental();
                System.out.println("Kiralama kapatıldı:");
                System.out.println(r);
                return;
            }
        }
        System.out.println("Rental bulunamadı.");
    }

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

    private static void exit() {
        System.out.println("Çıkılıyor...");
        sc.close();
        System.exit(0);
    }
}
