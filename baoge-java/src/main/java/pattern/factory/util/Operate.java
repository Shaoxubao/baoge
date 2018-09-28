package pattern.factory.util;

import java.util.Scanner;

public class Operate {

    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        String carName = "";
        System.out.println("用户输入车的名称：");
        carName = scan.next();
        return carName;                // 返回用户的输入值
    }


}
