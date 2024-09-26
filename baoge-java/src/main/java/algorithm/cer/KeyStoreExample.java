package algorithm.cer;// 导入所需的类
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class KeyStoreExample {
    public static void main(String[] args) throws Exception {
        // 创建一个新的密钥库
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        // 保存密钥库到文件
        FileOutputStream fos = new FileOutputStream("keystore.jks");
        keyStore.store(fos, "123456".toCharArray());

        // 关闭流
        fos.close();

        System.out.println("密钥库创建成功");
    }
}