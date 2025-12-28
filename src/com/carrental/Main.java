package com.carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarInventory inventory = new CarInventory();

        // Başlangıç araçları (ENUM kullanılıyor)
        inventory.addCar(new GasCar(1, "Toyota", "Corolla", 800, FuelType.BENZIN));
        inventory.addCar(new ElectricCar(2, "Tesla", "Model 3", 1200, 500));

        // Sistem kayıtları
        List<Customer> customers = new ArrayList<>();
        List<Rental> rentals = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        int nextCustomerId = 1;
        int nextRentalId = 1;
        int nextPaymentId = 1;

        while (true) {
            System.out.println("\n=== ARAÇ KİRALAMA SİSTEMİ ===");
            System.out.println("1 - Müsait araçları listele");
            System.out.println("2 - Araç kirala");
            System.out.println("3 - Araç iade et (Rental ID)");
            System.out.println("4 - Tüm araçları listele");
            System.out.println("5 - Kiralamaları listele");
            System.out.println("6 - Ödemeleri listele");
            System.out.println("0 - Çıkış");
            System.out.print("Seçim: ");

            int choice = sc.nextInt();
            sc.nextLine(); // buffer temizle

            try {
                switch (choice) {
                    case 1 -> inventory.listAvailableCars();

                    case 2 -> {
                        System.out.print("Araç ID: ");
                        int carId = sc.nextInt();
                        sc.nextLine();

                        Car car = inventory.findCarById(carId);
                        if (car == null) {
                            System.out.println("Araç bulunamadı.");
                            break;
                        }
                        if (!car.isAvailable()) {
                            System.out.println("Araç şu an kirada.");
                            break;
                        }

                        System.out.print("Müşteri adı: ");
                        String name = sc.nextLine();

                        System.out.print("Telefon: ");
                        String phone = sc.nextLine();

                        Customer customer = new Customer(nextCustomerId++, name, phone);
                        customers.add(customer);

                        System.out.print("Kaç gün kiralanacak? ");
                        int days = sc.nextInt();
                        sc.nextLine();

                        Rental rental = new Rental(nextRentalId++, car, customer, days);
                        rentals.add(rental);

                        System.out.print("Ödeme yöntemi (CASH / CARD): ");
                        PaymentMethod method =
                                PaymentMethod.valueOf(sc.nextLine().trim().toUpperCase());

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

                    case 3 -> {
                        System.out.print("İade edilecek Rental ID: ");
                        int rid = sc.nextInt();
                        sc.nextLine();

                        Rental found = null;
                        for (Rental r : rentals) {
                            if (r.getRentalId() == rid) {
                                found = r;
                                break;
                            }
                        }

                        if (found == null) {
                            System.out.println("Rental bulunamadı.");
                            break;
                        }

                        found.closeRental();
                        System.out.println("Kiralama kapatıldı:");
                        System.out.println(found);
                    }

                    case 4 -> inventory.listAllCars();

                    case 5 -> {
                        if (rentals.isEmpty()) {
                            System.out.println("Kiralama yok.");
                        } else {
                            for (Rental r : rentals) {
                                System.out.println(r);
                            }
                        }
                    }

                    case 6 -> {
                        if (payments.isEmpty()) {
                            System.out.println("Ödeme yok.");
                        } else {
                            for (Payment p : payments) {
                                System.out.println(p);
                            }
                        }
                    }

                    case 0 -> {
                        System.out.println("Çıkılıyor...");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Geçersiz seçim!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Hatalı giriş: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Beklenmeyen hata: " + e.getMessage());
            }
        }
    }
}
