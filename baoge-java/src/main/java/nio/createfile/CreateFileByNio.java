package nio.createfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * @Author shaoxubao
 * @Date 2019/12/3 17:18
 *
 * Java NIO 生成文件并写入指定count个数
 */
public class CreateFileByNio {

    private static final int LENGTH = 0;

    public static void main(String[] args) {
        String filePath = "D:\\files" + "/" + "tmp.txt";

        try {
            createFileByNio(filePath, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFileByNio(String filePath, int count) throws IOException {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        FileChannel channel = null;
        RandomAccessFile rf = null;
        try {
            File testFile = new File(filePath);
            if (testFile.exists())
                testFile.delete();
            // 如果文件不存在则创建
            testFile.createNewFile();

            rf = new RandomAccessFile(testFile, "rw");
            channel = rf.getChannel();
            int capacity = 1024 * 1024; // 字节
            ByteBuffer writerBuffer = ByteBuffer.allocate(capacity);
            // 一千
            for (long i = 0; i < count; i++) {
                stringBuffer.append(random.nextInt(100000)).append("\n");
                // 刷新到缓冲
                if ((i + 1) % 100 == 0) {
                    System.out.println("刷新到缓冲:" + stringBuffer.length());

                    writerBuffer.put(stringBuffer.toString().getBytes());
                    stringBuffer.setLength(LENGTH);
                    writerBuffer.flip();
                    channel.write(writerBuffer);
                    writerBuffer.clear();
                }
            }
        } catch (IOException e) {
            System.out.println("生成文件失败！" + e.getMessage());
            throw e;
        } finally {
            try {
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                // no-op
            }
            try {
                if (channel.isOpen()) {
                    channel.close();
                }
            } catch (IOException e) {
                // no-op
            }

        }
    }


}
