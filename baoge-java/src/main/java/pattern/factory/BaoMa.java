package pattern.factory;

public class BaoMa implements Car {

    private String brand = "宝马";         // 品牌
    private double price = 1000000;        // 价格

    public BaoMa() {
    }

    public BaoMa(String brand, double price) {
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
        return "Baoma [brand=" + brand + ", price=" + price + "]";
    }

    public void printInfo() {            //实现接口方法
        System.out.println("我是宝马:");
        System.out.println(this.toString());
    }
}
