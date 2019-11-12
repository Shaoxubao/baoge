package com.baoge.file_copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author shaoxubao
 * @Date 2019/11/12 9:59
 *
 * 按字节拷贝
 */
public class FileCopyByByte {
    public static void main(String[] args) throws IOException {
        textCopy();
    }

    public static void textCopy() throws IOException {
        System.out.println("开始: ... ");

        String fileName = "E:/webant.7z";
        FileInputStream  fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(fileName + "123.7z");
        int read = 0;
        long start = 0;
        while((read=fis.read()) != -1) {
            fos.write(read);
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        fis.close();
        fos.close();
    }
}
