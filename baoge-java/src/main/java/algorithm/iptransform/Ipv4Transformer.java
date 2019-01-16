package algorithm.iptransform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IPv4地址和int数字的互相转换
 */
public class Ipv4Transformer {

    private static final  Pattern NUM_PATTERN = Pattern.compile("\\d+");

    /**
     * IPv4地址转换为int类型数字
     */
    public static int ip2Integer(String ipv4Addr) throws Exception {
        if (!isIPv4Address(ipv4Addr)) {
            throw new Exception("IPv4 invalid addr");
        }
        int result = 0;

        int count = 0;
        Matcher matcher = NUM_PATTERN.matcher(ipv4Addr);
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group());
            result = (value << 8 * (3 - count++)) | result;
        }

        return result;
    }

    /**
     * 将int数字转换成ipv4地址
     */
    public static String integer2Ip(int ip) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        boolean needPoint = false; // 是否需要加入'.'
        for (int i = 0; i < 4; i++) {
            if (needPoint) {
                sb.append('.');
            }
            needPoint = true;
            int offset = 8 * (3 - i);
            num = (ip >> offset) & 0xff; // 0xff => 11111111
            sb.append(num);
        }

        return sb.toString();
    }

    /**
     * 判断是否为ipv4地址
     */
    private static boolean isIPv4Address(String ipv4Addr) {
        String lower = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])"; // 0-255的数字
        String regex = lower + "(\\." + lower + "){3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipv4Addr);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String ipAddr = "192.168.1.3";
        int intIp = -1062731517;
        try {
            System.out.println(ip2Integer(ipAddr));

            System.out.println(integer2Ip(intIp));
        } catch (Exception e) {

        }

    }

}
