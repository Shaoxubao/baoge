package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {

        // 以...开头正则表达
        String rex = "^ABC.*";

        String[] arr = {"ABCLP9", "ANB1233", "ABCdksk"};

        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            System.out.println(item.matches(rex));

            // 或者下列方式匹配
            Pattern pattern = Pattern.compile(rex);
            Matcher match = pattern.matcher(item);
            if (match.find()) {
                System.out.println(match.group());
            }
        }



    }

}
