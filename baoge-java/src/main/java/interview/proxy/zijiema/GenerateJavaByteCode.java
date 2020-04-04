package interview.proxy.zijiema;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/4
 */
public class GenerateJavaByteCode {

    public static void main(String[] args) throws Exception {
        // 生成一个代理字节码数组
        byte[] bytes = ProxyGenerator.generateProxyClass("UserService$proxy", new Class[]{UserService.class});

        String fileName = System.getProperty("user.dir") + "/baoge-java/target/UserService$proxy.class";
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();

    }

}
