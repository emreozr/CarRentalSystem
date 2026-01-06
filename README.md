# 🚗 Car Rental System – Java Console Application

## 📌 Proje Tanımı
Bu proje, **Nesne Tabanlı Programlama (Object-Oriented Programming)** dersinin gereksinimlerini karşılamak amacıyla geliştirilmiş, **konsol tabanlı bir Araç Kiralama Sistemi**dir.

Sistem; farklı araç türlerinin kiralanmasını, müşteri yönetimini, kiralama ve ödeme süreçlerini ve bu süreçlerin yaşam döngüsünü (lifecycle) yönetir. Proje boyunca OOP prensipleri gerçek bir senaryo üzerinden uygulanmıştır.

---

## 🎯 Projenin Amaçları
- OOP kavramlarını (Inheritance, Polymorphism, Abstraction, Encapsulation) etkin kullanmak
- Enum ve Custom Exception yapılarıyla güvenli durum yönetimi sağlamak
- Gerçekçi bir **rental & payment lifecycle** modellemek
- Okunabilir, yorumlanmış ve sürdürülebilir kod yazmak
- Git, Kanban ve CI kavramlarını proje sürecine entegre etmek

---

## 🧱 Kullanılan Teknolojiler
- **Java (JDK 17+)**
- **Console (CLI)**
- **Git & GitHub**
- **GitHub Actions (YAML – CI)**

---

## 🧠 Mimari ve Katmanlı Yapı

Uygulama sade ama bilinçli bir mimariyle tasarlanmıştır:

Main (Controller / UI Layer)
│
├── Inventory Management
│ └── CarInventory
│
├── Domain Models
│ ├── Car (abstract)
│ │ ├── GasCar
│ │ ├── ElectricCar
│ │ └── LuxuryCar
│ ├── Customer
│ ├── Rental
│ └── Payment
│
├── Interfaces
│ └── Rentable
│
├── Enums
│ ├── FuelType
│ ├── PaymentMethod
│ ├── PaymentStatus
│ └── RentalStatus
│
└── Exceptions
├── CarNotAvailableException
├── InvalidRentalPeriodException
└── RentalAlreadyClosedException


---

## 🚘 Araç Türleri

### 🔹 GasCar
- Yakıt türüne sahiptir (`BENZIN`, `DIZEL`, `LPG`)
- Sabit servis ücreti eklenir
- `calculateRentalFee()` override edilmiştir

### 🔹 ElectricCar
- Menzil bilgisi içerir
- Daha düşük bakım/batarya ücreti uygulanır
- Polymorphism örneği

### 🔹 LuxuryCar
- Premium araç türüdür
- Günlük ücret üzerine %30 (veya özel oran) eklenir
- Inheritance ve polymorphism’in güçlü bir örneğidir

---

## 🔄 Rental Lifecycle (Kiralama Yaşam Döngüsü)

Kiralama süreci `RentalStatus` enum ile yönetilir:

CREATED → ACTIVE → COMPLETED


- **CREATED**: Kiralama oluşturuldu
- **ACTIVE**: Araç kiralandı
- **COMPLETED**: Araç iade edildi

Bu yapı sayesinde:
- Aynı kiralama tekrar kapatılamaz
- Hatalı durum geçişleri engellenir

---

## 💳 Payment Lifecycle (Ödeme Yaşam Döngüsü)

Ödeme süreci `PaymentStatus` enum ile yönetilir:

PAID → REFUNDED


Desteklenen ödeme yöntemleri:
- CASH
- CARD
- TRANSFER
- MOBILE_PAY

---

## 🧩 Kullanılan OOP Kavramları

### ✔ Encapsulation
- Tüm alanlar `private`
- Erişim getter metotları ile sağlanır

### ✔ Inheritance
- `Car` → `GasCar`, `ElectricCar`, `LuxuryCar`

### ✔ Polymorphism
- `calculateRentalFee()` her araç türünde farklı davranır

### ✔ Abstraction
- `Car` abstract sınıf
- `Rentable` interface

### ✔ Enum Kullanımı
- State ve tip güvenliği sağlar

### ✔ Custom Exception
- İş kurallarını zorunlu hale getirir
- Hatalı durumları önler

---

## 🖥️ Kullanıcı İşlevleri (Console Menü)

Kullanıcı aşağıdaki işlemleri yapabilir:
- Müsait araçları listeleme
- Araç kiralama
- Araç iade etme
- Tüm araçları görüntüleme
- Kiralama kayıtlarını listeleme
- Ödeme kayıtlarını listeleme
- Araç filtreleme (marka, yakıt türü, araç tipi)
- Araç ekleme / silme
- Receipt (fiş) yazdırma

---

## 🧪 Unit Testing (Teorik Açıklama)

Bu projede unit test kodu yazılmamıştır ancak:
- Her sınıf tek sorumluluk ilkesine uygundur
- Methodlar test edilebilir yapıdadır

Olası test senaryoları:
- Araç müsait değilken kiralama
- Negatif gün sayısı ile kiralama
- Kiralamanın ikinci kez kapatılması
- Ödeme iadesinin tekrar denenmesi

---

## 🧭 Kanban ve Proje Yönetimi

Proje geliştirme süreci **Kanban Board** kullanılarak planlanmıştır.

Kanban sütunları:
- **To Do**: Yapılacak işler
- **In Progress**: Üzerinde çalışılanlar
- **Done**: Tamamlanan görevler

Her büyük özellik (rental lifecycle, payment sistemi, filtreleme, luxury car vb.)
ayrı task olarak ele alınmıştır.

---

## 🔁 Git & Commit Süreci

Proje boyunca **küçük ve anlamlı commitler** atılmıştır.

Örnek commit türleri:
- Feature ekleme
- Lifecycle geliştirme
- UI iyileştirme
- Yorum ve dokümantasyon ekleme

Bu yaklaşım, yazılım geliştirme sürecini daha izlenebilir hale getirmiştir.

---

## ⚙️ CI – GitHub Actions (YAML)

Projede yazılım yaşam döngüsünü desteklemek amacıyla **GitHub Actions** kullanılmıştır.

Her `push` işleminde:
- Java dosyaları otomatik olarak derlenir

Kullanılan yapılandırma `.github/workflows/java-ci.yml` dosyasında yer almaktadır.

Bu yapı:
- Kod kalitesini korumayı
- Hataları erken tespit etmeyi
amaçlar.

---

## 🚀 Çalıştırma

1. Projeyi Eclipse veya IntelliJ ile aç
2. `Main.java` dosyasını çalıştır
3. Konsol menüsü üzerinden sistemi kullan

---

## 🔮 Gelecek Geliştirmeler
- Veritabanı entegrasyonu (SQLite / MySQL)
- GUI (JavaFX / Swing)
- Gerçek unit testler (JUnit)
- Kullanıcı girişi ve rol yönetimi
- Dosya bazlı kalıcılık (persistency)

---

## 👩‍💻 Geliştirici
- **İsim:** Merve  
- **Ders:** Nesne Tabanlı Programlama  
- **Dil:** Java  

---

## 📄 Lisans
Bu proje akademik amaçlı geliştirilmiştir.
