package com.baoge.file_copy;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author shaoxubao
 * @Date 2019/11/12 10:11
 *
 * NIO+零拷贝
 */
public class FileCopyByBioAndZero {

    public static void main(String[] args) {
        copy1(); // 180ms
        copy2(); // 106ms
    }

    private static void copy1() {
        try {
            // 直接获取通道
            FileChannel inChannel2 = FileChannel.open(Paths.get("E:/webant.7z"), StandardOpenOption.READ);
            FileChannel outChannel2 = FileChannel.open(Paths.get("E:/output123.7z"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            // 内存映射文件
            MappedByteBuffer inMappedBuf = inChannel2.map(FileChannel.MapMode.READ_ONLY, 0, inChannel2.size());
            MappedByteBuffer outMappedBuf = outChannel2.map(FileChannel.MapMode.READ_WRITE, 0, inChannel2.size());

            // 直接对缓冲区进行数据读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            long start = System.currentTimeMillis();
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);

            System.out.println("耗费的时间为：" + ( System.currentTimeMillis() - start));
            inChannel2.close();
            outChannel2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy2() {
        try {

            // 通道之间的数据传输（直接缓冲区）
            FileChannel inChannel3 = FileChannel.open(Paths.get("E:/webant.7z"), StandardOpenOption.READ);
            FileChannel outChannel3 = FileChannel.open(Paths.get("E:/output1234.7z"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
            long start = System.currentTimeMillis();
            inChannel3.transferTo(0, inChannel3.size(), outChannel3);

            System.out.println("耗时: "+(System.currentTimeMillis() - start));
            //等价于
            // outChannel3.transferFrom(inChannel3, 0, inChannel3.size());

            inChannel3.close();
            outChannel3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
