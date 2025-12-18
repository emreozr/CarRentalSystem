package com.carrental;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarInventory inventory = new CarInventory();

        // Başlangıç araçları
        inventory.addCar(new GasCar(1, "Toyota", "Corolla", 800, "Benzin"));
        inventory.addCar(new ElectricCar(2, "Tesla", "Model 3", 1200, 500));

        while (true) {
            System.out.println("\n=== ARAÇ KİRALAMA SİSTEMİ ===");
            System.out.println("1 - Müsait araçları listele");
            System.out.println("2 - Araç kirala");
            System.out.println("3 - Araç iade et");
            System.out.println("4 - Tüm araçları listele");
            System.out.println("0 - Çıkış");
            System.out.print("Seçim: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> inventory.listAvailableCars();

                    case 2 -> {
                        System.out.print("Araç ID: ");
                        int id = sc.nextInt();
                        Car car = inventory.findCarById(id);

                        if (car == null) {
                            System.out.println("Araç bulunamadı.");
                            break;
                        }
                        if (!car.isAvailable()) {
                            System.out.println("Araç şu an kirada.");
                            break;
                        }

                        System.out.print("Kaç gün kiralanacak? ");
                        int days = sc.nextInt();

                        double fee = car.calculateRentalFee(days);
                        car.rent();

                        System.out.println("Araç kiralandı. Ücret: " + fee + " TL");
                    }

                    case 3 -> {
                        System.out.print("İade edilecek araç ID: ");
                        int id = sc.nextInt();
                        Car car = inventory.findCarById(id);

                        if (car == null) {
                            System.out.println("Araç bulunamadı.");
                            break;
                        }

                        car.returnCar();
                        System.out.println("Araç başarıyla iade edildi.");
                    }

                    case 4 -> inventory.listAllCars();

                    case 0 -> {
                        System.out.println("Çıkılıyor...");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Geçersiz seçim!");
                }
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }
}
