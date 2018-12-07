package concurrent.thread.jioujiaoti.type1;

public class Main {

    public static void main(String[] args) {

        Num num = new Num();

        PrintOdd printOdd = new PrintOdd(num);
        PrintEven printEven = new PrintEven(num);

        Thread thread1 = new Thread(printOdd);
        Thread thread2 = new Thread(printEven);

        thread1.start();
        thread2.start();

    }

}
