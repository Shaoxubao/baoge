package algorithm.cer;// 导入所需的类
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class CerCertificateExample {
    public static void main(String[] args) throws Exception {
        // 加载密钥库
        FileInputStream fis = new FileInputStream("keystore.jks");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(fis, "123456".toCharArray());

        // 获取证书
        Certificate certificate = keyStore.getCertificate("alias");

        // 导出证书为CER文件
        FileOutputStream fos = new FileOutputStream("certificate.cer");
        fos.write(certificate.getEncoded());

        // 关闭流
        fos.close();
    }
}