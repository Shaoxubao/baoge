package pattern.factory;

public class DaZhong implements Car {

    private String brand = "大众";        // 品牌
    private double price = 100000;        // 价格

    public DaZhong() {
    }

    public DaZhong(String brand, double price) {
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
        return "DaZhong [brand=" + brand + ", price=" + price + "]";
    }

    public void printInfo() {            //实现接口方法
        System.out.println("我是大众:");
        System.out.println(this.toString());
    }
}
