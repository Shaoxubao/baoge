package nio.filecopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/31
 *
 * 堆内存复制
 */
public class AllocationDemo {

    public static void copyFileByAllocation(int size) {
        try {
            FileInputStream fis = new FileInputStream("d:/实战java高并发程序设计.pdf");
            FileChannel fcIn = fis.getChannel();

            FileOutputStream fos = new FileOutputStream("d:/" + System.currentTimeMillis() + ".pdf");
            FileChannel fcOut = fos.getChannel();

//            ByteBuffer buf = ByteBuffer.allocate(size);
            ByteBuffer buf = ByteBuffer.allocateDirect(size); // 直接堆内存分配

            long startTime = System.currentTimeMillis();
            while (fcIn.read(buf) != -1) {
                buf.flip();
                fcOut.write(buf);
                buf.clear();
            }

            fcOut.close();
            fcIn.close();

            System.out.println(size + ": " + (System.currentTimeMillis() - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 32; i = i * 2) {
            copyFileByAllocation(i * 1024);
        }

    }

}
