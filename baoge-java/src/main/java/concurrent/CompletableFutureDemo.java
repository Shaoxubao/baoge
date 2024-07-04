package concurrent;

import concurrent.future.MqttDeliveryFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        MqttDeliveryFuture future = demo();
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MqttDeliveryFuture demo()  {
        MqttDeliveryFuture future = new MqttDeliveryFuture();
        int a = 1 + 1;
        future.complete(Boolean.TRUE);
        return future;
    }
}
