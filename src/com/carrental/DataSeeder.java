package com.carrental;

import java.util.ArrayList;
import java.util.List;

public class DataSeeder {

    private DataSeeder() {}

    public static List<Car> seedCars() {
        List<Car> cars = new ArrayList<>();

        // 1..40 GasCar
        cars.add(new GasCar(1, "Toyota", "Corolla", 800, FuelType.BENZIN));
        cars.add(new GasCar(2, "Toyota", "Yaris", 700, FuelType.DIZEL));
        cars.add(new GasCar(3, "Renault", "Clio", 650, FuelType.LPG));
        cars.add(new GasCar(4, "Renault", "Megane", 850, FuelType.DIZEL));
        cars.add(new GasCar(5, "Fiat", "Egea", 720, FuelType.BENZIN));
        cars.add(new GasCar(6, "Fiat", "Linea", 600, FuelType.LPG));
        cars.add(new GasCar(7, "Ford", "Focus", 820, FuelType.BENZIN));
        cars.add(new GasCar(8, "Ford", "Fiesta", 690, FuelType.DIZEL));
        cars.add(new GasCar(9, "Hyundai", "i20", 710, FuelType.BENZIN));
        cars.add(new GasCar(10, "Hyundai", "i10", 620, FuelType.LPG));
        cars.add(new GasCar(11, "Volkswagen", "Polo", 780, FuelType.BENZIN));
        cars.add(new GasCar(12, "Volkswagen", "Golf", 980, FuelType.DIZEL));
        cars.add(new GasCar(13, "Opel", "Corsa", 740, FuelType.BENZIN));
        cars.add(new GasCar(14, "Opel", "Astra", 900, FuelType.DIZEL));
        cars.add(new GasCar(15, "Peugeot", "208", 760, FuelType.BENZIN));
        cars.add(new GasCar(16, "Peugeot", "301", 730, FuelType.DIZEL));
        cars.add(new GasCar(17, "Citroen", "C3", 750, FuelType.BENZIN));
        cars.add(new GasCar(18, "Citroen", "C-Elysee", 720, FuelType.DIZEL));
        cars.add(new GasCar(19, "Honda", "Civic", 980, FuelType.BENZIN));
        cars.add(new GasCar(20, "Honda", "City", 900, FuelType.DIZEL));

        cars.add(new GasCar(21, "Skoda", "Fabia", 770, FuelType.BENZIN));
        cars.add(new GasCar(22, "Skoda", "Octavia", 1050, FuelType.DIZEL));
        cars.add(new GasCar(23, "Seat", "Ibiza", 780, FuelType.BENZIN));
        cars.add(new GasCar(24, "Seat", "Leon", 980, FuelType.DIZEL));
        cars.add(new GasCar(25, "Kia", "Rio", 710, FuelType.BENZIN));
        cars.add(new GasCar(26, "Kia", "Ceed", 920, FuelType.DIZEL));
        cars.add(new GasCar(27, "Nissan", "Micra", 700, FuelType.BENZIN));
        cars.add(new GasCar(28, "Nissan", "Juke", 980, FuelType.DIZEL));
        cars.add(new GasCar(29, "Dacia", "Sandero", 640, FuelType.LPG));
        cars.add(new GasCar(30, "Dacia", "Duster", 920, FuelType.DIZEL));
        cars.add(new GasCar(31, "BMW", "320i", 1600, FuelType.BENZIN));
        cars.add(new GasCar(32, "Mercedes", "C200", 1700, FuelType.BENZIN));
        cars.add(new GasCar(33, "Audi", "A3", 1650, FuelType.DIZEL));
        cars.add(new GasCar(34, "Volvo", "S60", 1750, FuelType.DIZEL));
        cars.add(new GasCar(35, "Mazda", "3", 1200, FuelType.BENZIN));
        cars.add(new GasCar(36, "Suzuki", "Swift", 680, FuelType.BENZIN));
        cars.add(new GasCar(37, "Mitsubishi", "Lancer", 950, FuelType.BENZIN));
        cars.add(new GasCar(38, "Chevrolet", "Cruze", 900, FuelType.LPG));
        cars.add(new GasCar(39, "Toyota", "Camry", 1400, FuelType.BENZIN));
        cars.add(new GasCar(40, "Renault", "Symbol", 600, FuelType.DIZEL));

        // 41..45 ElectricCar (5 adet)
        cars.add(new ElectricCar(41, "Tesla", "Model 3", 1200, 500));
        cars.add(new ElectricCar(42, "Tesla", "Model Y", 1400, 480));
        cars.add(new ElectricCar(43, "Togg", "T10X", 1100, 520));
        cars.add(new ElectricCar(44, "Volkswagen", "ID.4", 1250, 420));
        cars.add(new ElectricCar(45, "Hyundai", "Ioniq 5", 1300, 450));

        // 46..50 LuxuryCar (5 adet)
        cars.add(new LuxuryCar(46, "BMW", "7 Series", 2500));
        cars.add(new LuxuryCar(47, "Mercedes", "S Class", 2700));
        cars.add(new LuxuryCar(48, "Audi", "A8", 2600));
        cars.add(new LuxuryCar(49, "Porsche", "Panamera", 3200, 0.40));
        cars.add(new LuxuryCar(50, "Jaguar", "XJ", 2800, 0.35));

        return cars;
    }
}
