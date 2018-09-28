package pattern.factory.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Utils {

    public static Properties getProperty() {

        Properties pro = new Properties();        // 声明对象

        // 实例化File类对象
        File file = new File("D:" + File.separator + "init.properties");

        try {
            if (file.exists()) {                            // 属性文件存在
                pro.load(new FileInputStream(file));        // 加载文件
            } else {
                // 文件不存在，编辑文件内容
                pro.setProperty("大众", "pattern.factory.DaZhong");
                pro.setProperty("宝马", "pattern.factory.BaoMa");
                pro.setProperty("奥迪", "pattern.factory.AoDi");
                // 进行存储
                pro.store(new FileOutputStream(file), "The information of the car");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return pro;
    }

}
