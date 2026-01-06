package com.carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the Car Rental System (Console Application).
 *
 * <p>This class is responsible for:
 * <ul>
 *   <li>Reading user input via console</li>
 *   <li>Displaying menus</li>
 *   <li>Coordinating core operations (inventory, rental, payment)</li>
 * </ul>
 * </p>
 *
 * <p>Note: Business logic is mostly kept inside model/service-like classes
 * (Car, Rental, Payment, CarInventory). Main acts as the controller layer
 * for user interaction.</p>
 */
public class Main {

    /** Single Scanner instance used for all console input. */
    private static final Scanner sc = new Scanner(System.in);

    /** Inventory stores all cars (GasCar / ElectricCar / LuxuryCar). */
    private static final CarInventory inventory = new CarInventory();

    /** In-memory data stores for the application runtime. */
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Rental> rentals = new ArrayList<>();
    private static final List<Payment> payments = new ArrayList<>();

    /** Simple ID generators for customers, rentals and payments. */
    private static int nextCustomerId = 1;
    private static int nextRentalId = 1;
    private static int nextPaymentId = 1;

    /**
     * Main loop of the application.
     *
     * <p>Steps:
     * <ol>
     *   <li>Seed initial data (cars)</li>
     *   <li>Show menu</li>
     *   <li>Read choice</li>
     *   <li>Dispatch to the related handler</li>
     * </ol>
     * </p>
     */
    public static void main(String[] args) {

        // Seed initial cars (Gas + Electric + Luxury)
        inventory.addCars(DataSeeder.seedCars());

        // Main application loop: keeps running until user selects exit
        while (true) {
            printMenu();
            int choice = readInt("Seçim: ");

            try {
                // Menu dispatch: routes user choice to the correct operation
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
                // Centralized error handling: prevents app crash and shows user-friendly message
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }

    // ================= MENU =================

    /**
     * Prints the main menu to the console.
     * Only UI output (no business logic).
     */
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

    /**
     * Reads an integer input from the user safely.
     *
     * <p>If user enters non-numeric input, it keeps prompting until
     * a valid integer is entered.</p>
     *
     * @param label prompt label
     * @return parsed integer value
     */
    private static int readInt(String label) {
        System.out.print(label);
        while (!sc.hasNextInt()) {
            sc.nextLine(); // clear invalid input
            System.out.print("Geçerli bir sayı giriniz: ");
        }
        int val = sc.nextInt();
        sc.nextLine(); // consume newline
        return val;
    }

    /**
     * Reads a positive integer (>= 1).
     *
     * @param label prompt label
     * @return positive integer value
     */
    private static int readPositiveInt(String label) {
        int val;
        do {
            val = readInt(label);
            if (val <= 0) System.out.println("Değer 1 veya daha büyük olmalı.");
        } while (val <= 0);
        return val;
    }

    /**
     * Reads a trimmed text input line.
     *
     * @param label prompt label
     * @return trimmed input string
     */
    private static String readLine(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    /**
     * Reads a phone number that contains only digits (10-11 length).
     * Basic validation for OOP project requirements.
     *
     * @param label prompt label
     * @return validated phone number
     */
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

    /**
     * Reads payment method as enum value.
     * Keeps asking until a valid PaymentMethod is entered.
     *
     * @param label prompt label
     * @return selected PaymentMethod
     */
    private static PaymentMethod readPaymentMethod(String label) {
        while (true) {
            try {
                return PaymentMethod.valueOf(readLine(label).toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Geçersiz yöntem (CASH / CARD / TRANSFER / MOBILE_PAY)");
            }
        }
    }

    /**
     * Reads fuel type as enum value.
     * Keeps asking until a valid FuelType is entered.
     *
     * @param label prompt label
     * @return selected FuelType
     */
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

    /**
     * Handles the rental operation:
     * <ol>
     *   <li>Select a car</li>
     *   <li>Create customer</li>
     *   <li>Create rental (lifecycle becomes ACTIVE)</li>
     *   <li>Create payment record</li>
     * </ol>
     */
    private static void handleRent() {
        int carId = readInt("Araç ID: ");
        Car car = inventory.findCarById(carId);

        // Validate selected car
        if (car == null) {
            System.out.println("Araç bulunamadı.");
            return;
        }

        // Prevent renting a car that is already rented
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Araç şu an kirada.");
        }

        // Collect customer information
        String name = readLine("Müşteri adı: ");
        String phone = readPhone("Telefon: ");

        // Create and store customer (immutable entity)
        Customer customer = new Customer(nextCustomerId++, name, phone);
        customers.add(customer);

        // Rental duration input
        int days = readPositiveInt("Kaç gün kiralanacak? ");

        // Create rental (internally marks car as rented, status becomes ACTIVE)
        Rental rental = new Rental(nextRentalId++, car, customer, days);
        rentals.add(rental);

        // Payment input + record creation
        PaymentMethod method =
                readPaymentMethod("Ödeme yöntemi (CASH / CARD / TRANSFER / MOBILE_PAY): ");

        Payment payment = new Payment(
                nextPaymentId++,
                rental.getRentalId(),
                rental.getTotalFee(),
                method
        );
        payments.add(payment);

        // User feedback
        System.out.println("Kiralama başarılı:");
        System.out.println(rental);
        System.out.println("Ödeme alındı:");
        System.out.println(payment);
    }

    /**
     * Handles returning a car by closing an existing rental.
     * Closing the rental updates its lifecycle to COMPLETED and returns the car to available state.
     */
    private static void handleReturn() {
        int rid = readInt("İade edilecek Rental ID: ");

        // Find the rental by ID
        for (Rental r : rentals) {
            if (r.getRentalId() == rid) {
                // Close rental => car.returnCar() + status COMPLETED
                r.closeRental();
                System.out.println("Araç iade edildi:");
                System.out.println(r);
                return;
            }
        }

        System.out.println("Rental bulunamadı.");
    }

    // ================= LIST =================

    /** Lists all rentals (active + completed). */
    private static void listRentals() {
        if (rentals.isEmpty()) {
            System.out.println("Kiralama yok.");
            return;
        }
        rentals.forEach(System.out::println);
    }

    /** Lists all payment records (paid/refunded). */
    private static void listPayments() {
        if (payments.isEmpty()) {
            System.out.println("Ödeme yok.");
            return;
        }
        payments.forEach(System.out::println);
    }

    // ================= FILTER =================

    /**
     * Filtering submenu for available cars.
     * Demonstrates working with lists and polymorphic car types stored as Car.
     */
    private static void handleFilteringMenu() {
        System.out.println("\n--- FİLTRELEME ---");
        System.out.println("1 - Markaya göre");
        System.out.println("2 - Yakıt türüne göre");
        System.out.println("3 - GasCar");
        System.out.println("4 - ElectricCar");
        System.out.println("0 - Geri dön");

        int c = readInt("Seçim: ");

        // Filters use CarInventory methods to keep inventory logic in one place
        switch (c) {
            case 1 -> printCars(inventory.filterAvailableByBrand(readLine("Marka: ")));
            case 2 -> printCars(inventory.filterAvailableByFuelType(readFuelType("Yakıt: ")));
            case 3 -> printCars(inventory.filterAvailableGasCars());
            case 4 -> printCars(inventory.filterAvailableElectricCars());
            case 0 -> { return; }
            default -> System.out.println("Geçersiz seçim!");
        }
    }

    /**
     * Prints a list of cars to the console.
     *
     * @param cars list of cars to print
     */
    private static void printCars(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("Sonuç yok.");
            return;
        }
        cars.forEach(System.out::println);
    }

    // ================= INVENTORY =================

    /**
     * Adds a new car to the inventory based on selected type.
     * Demonstrates inheritance (Car subclasses) and polymorphic behavior.
     */
    private static void handleAddCar() {
        System.out.println("\n--- ARAÇ EKLE ---");
        System.out.println("1 - GasCar");
        System.out.println("2 - ElectricCar");
        System.out.println("3 - LuxuryCar");

        int t = readInt("Tür: ");

        // Basic car fields
        int id = readInt("ID: ");
        String brand = readLine("Marka: ");
        String model = readLine("Model: ");

        // Note: dailyRate is read as positive int but stored in double for pricing operations
        double dailyRate = readPositiveInt("Günlük ücret: ");

        Car car;

        // Create correct subclass based on user choice
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

        // Inventory enforces unique ID and non-null validations
        inventory.addCar(car);
        System.out.println("Araç eklendi: " + car);
    }

    /**
     * Removes a car from inventory by its ID.
     * Uses CarInventory to keep inventory logic encapsulated.
     */
    private static void handleRemoveCar() {
        int id = readInt("Silinecek araç ID: ");
        System.out.println(
                inventory.removeCar(id) ? "Araç silindi." : "Araç bulunamadı."
        );
    }

    // ================= RECEIPT =================

    /**
     * Prints a receipt for a given rental ID.
     *
     * <p>This method finds the rental and its related payment and prints them.
     * It is a read-only operation (does not modify any state).</p>
     */
    private static void printReceipt() {
        int rid = readInt("Rental ID: ");

        // Find rental by ID
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

        // Find payment by rental ID
        Payment payment = null;
        for (Payment p : payments) {
            if (p.getRentalId() == rid) {
                payment = p;
                break;
            }
        }

        // Receipt output
        System.out.println("\n===== RECEIPT =====");
        System.out.println(rental);
        if (payment != null) System.out.println(payment);
        else System.out.println("Ödeme bulunamadı.");
        System.out.println("===================");
    }

    // ================= EXIT =================

    /**
     * Exits the application safely.
     * Closes scanner and terminates the JVM.
     */
    private static void exit() {
        System.out.println("Çıkılıyor...");
        sc.close();
        System.exit(0);
    }
}
