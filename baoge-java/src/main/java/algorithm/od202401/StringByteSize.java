package algorithm.od202401;

public class StringByteSize {
    public static void main(String[] args) {
        String str = "111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000"; // 示例字符串
        int strLen = str.length(); // 获取字符串的字符数
        int byteSize = strLen * 2; // 假设使用UTF-16，每个字符2字节
 
        // 如果使用UTF-8，需要进一步计算字节数
        byteSize = str.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;

        System.out.println("字符串字符数: " + strLen);
        System.out.println("字符串在UTF-8字符集下占用的字节数: " + byteSize + " 字节");
    }
}