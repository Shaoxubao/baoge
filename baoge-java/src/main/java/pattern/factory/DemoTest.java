package pattern.factory;

import pattern.factory.util.Operate;
import pattern.factory.util.Utils;

import java.util.Properties;

public class DemoTest {

    public static void main(String[] args) {
        String carName = Operate.getInput();             // 取得用户的输入内容（此处没有加数据的合法验证）
        Properties pro = Utils.getProperty();            // 数理化Properties对象
        Car car = Factory.getInstance( pro.getProperty(carName) );    // 通过属性文件取得实例化对象，并实例化Car对象
        car.printInfo();                                 // 调用被实例对象覆写的方法
    }

}
