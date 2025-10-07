import java.util.*;

// 👉 Giao diện phương thức thanh toán
interface PaymentMethod {
    void pay(double total, String customerName);
}

// 👉 Các cách thanh toán khác nhau
class CashPayment implements PaymentMethod {
    public void pay(double total, String name) {
        System.out.println(name + " thanh toan " + total + " bằng tiền mặt.");
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double total, String name) {
        System.out.println(name + " thanh toan " + total + " bang the tin dung.");
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double total, String name) {
        System.out.println(name + " thanh toan " + total + " bang vi Momo.");
    }
}

class Product {
    String id;
    String name;
    double price;

    Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    double getPrice() {
        return price;
    }

    public String toString() {
        return name + " - " + price + "đ";
    }
}


class ElectronicProduct extends Product {
    String imei;
    int warranty;

    ElectronicProduct(String id, String name, double price, String imei, int warranty) {
        super(id, name, price);
        this.imei = imei;
        this.warranty = warranty;
    }

    public String toString() {
        return name + " (IMEI: " + imei + ", BH: " + warranty + " thang)";
    }
}


class FoodProduct extends Product {
    String expiryDate;

    FoodProduct(String id, String name, double price, String expiryDate) {
        super(id, name, price);
        this.expiryDate = expiryDate;
    }

    public String toString() {
        return name + " (HSD: " + expiryDate + ")";
    }
}

// 👉 Lớp đơn hàng
class Order {
    String customerName;
    ArrayList<Product> products = new ArrayList<>();
    PaymentMethod payment;

    Order(String name) {
        this.customerName = name;
    }

    void addProduct(Product p) {
        products.add(p);
    }

    double getTotal() {
        double total = 0;
        for (Product p : products) total += p.getPrice();
        return total;
    }

    void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    void checkout() {
        if (payment == null) {
            System.out.println("Chưa chọn phương thức thanh toan!");
            return;
        }
        payment.pay(getTotal(), customerName);
    }
}

// 👉 Lớp chạy chính
public class Main {
    public static void main(String[] args) {

        Product phone = new ElectronicProduct("E01", "Ipad", 15000000, "123456789", 12);
        Product cookie = new FoodProduct("F01", "Cake", 35000, "31/12/2025");

        // Đơn hàng 1 - Tiền mặt
        Order o1 = new Order("Nguyen Tien A");
        o1.addProduct(phone);
        o1.addProduct(cookie);
        o1.setPayment(new CashPayment());
        o1.checkout();

        System.out.println("----------------");

   
        Order o2 = new Order("Tran Van B");
        o2.addProduct(phone);
        o2.setPayment(new CreditCardPayment());
        o2.checkout();

        System.out.println("----------------");

   
        Order o3 = new Order("Dang Thi C");
        o3.addProduct(cookie);
        o3.setPayment(new MomoPayment());
        o3.checkout();
    }
}
