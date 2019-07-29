package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {

        String rex = "^ABC.*";

        String[] arr = {"ABCLP9", "ANB1233", "ABCdksk"};


        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            System.out.println(item.matches(rex));
        }

    }

}
