package concurrent;

public class VolatileDemo {

    private static volatile VolatileDemo instance;

    public static VolatileDemo getInstance() {
        if (instance == null) {
            instance = new VolatileDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileDemo.getInstance();
    }

}
