package algorithm.cer;// 导入所需的类
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.cert.Certificate;

public class KeyPairExample {
    public static void main(String[] args) throws Exception {
        // 加载密钥库
        FileInputStream fis = new FileInputStream("keystore.jks");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(fis, "123456".toCharArray());

        // 生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 将公钥保存到证书
        Certificate certificate = keyStore.getCertificate("alias");
        certificate.verify(publicKey);

        // 将私钥保存到密钥库
        keyStore.setKeyEntry("alias", privateKey, "123456".toCharArray(), new Certificate[]{certificate});

        // 保存更新后的密钥库到文件
        FileOutputStream fos = new FileOutputStream("keystore.jks");
        keyStore.store(fos, "123456".toCharArray());

        // 关闭流
        fis.close();
        fos.close();

        System.out.println("密钥对生成成功");
    }
}