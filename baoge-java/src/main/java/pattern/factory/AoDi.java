package pattern.factory;

public class AoDi implements Car {

    private String brand = "奥迪";          // 品牌
    private double price = 10000000;        // 价格

    public AoDi() {
    }


    public AoDi(String brand, double price) {

        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AoDi [brand=" + brand + ", price=" + price + "]";
    }

    public void printInfo() {
        System.out.println("我是奥迪:");
        System.out.println(this.toString());
    }
}
