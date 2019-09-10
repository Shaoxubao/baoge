package utils;

import java.io.*;

public class FileUtil {

    public static BufferedReader br;

    /**获取BufferedReader,用于读取*/
    public static BufferedReader openFile(String fileName) throws IOException {
        InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        return br;
    }

    /**读取文件一行内容*/
    public static String readLine() throws IOException {
        return br.readLine();
    }

    /**关闭输入流*/
    public static void closeFile() throws IOException {
        br.close();
    }

    /**将json字符串写入文件*/
    public static void writeFileToJson(String filePath, String sets) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        fw.close();
        out.close();
    }
}
