package com.baoge.file_copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author shaoxubao
 * @Date 2019/11/12 10:06
 *
 * 以NIO,channel+buffer的模型,拷贝文件
 */
public class FileCopyByNio {
    public static void main(String[] args) {

        String fileName = "E:/webant.7z";
        try {
            FileInputStream fis = new FileInputStream(fileName);
            FileOutputStream fos = new FileOutputStream(fileName + "1234.7z");
            // 1.获取通道
            FileChannel inChannel = fis.getChannel();
            FileChannel   outChannel = fos.getChannel();
            // 2.分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long start = System.currentTimeMillis();

            // 3.将通道中的数据缓冲区中
            while (inChannel.read(buffer) != -1) {
                buffer.flip(); // 切换成都数据模式
                // 4.将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                buffer.clear(); // 清空缓冲区
            }
            System.out.println("总耗时:" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
