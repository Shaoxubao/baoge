package pattern.factory;

public class Factory {

    public static Car getInstance(String carName) {                 // 此处传递过来的参数是 包.类名
        Car car = null;

        try {
            car = (Car) Class.forName(carName).newInstance();        // 实例化对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

}
