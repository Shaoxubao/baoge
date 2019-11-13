package utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    private static MessageDigest md = null;
    private static final String[] charDigits;

    public Md5Utils() {
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (bByte < 0) {
            iRet = bByte + 256;
        }

        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return charDigits[iD1] + charDigits[iD2];
    }

    private static String byteToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; ++i) {
            sb.append(byteToArrayString(bytes[i]));
        }

        return sb.toString();
    }

    public static synchronized String getMd5ByStr(String str) {
        return byteToString(md.digest(str.getBytes(Charset.forName("UTF-8"))));
    }

    public static synchronized String getMd5ByBytes(byte[] bytes) {
        return byteToString(md.digest(bytes));
    }

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
            ;
        }

        charDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    }
}
