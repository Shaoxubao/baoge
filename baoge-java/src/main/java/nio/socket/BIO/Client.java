package nio.socket.BIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/26
 */
public class Client {
    final static String ADDRESS = "127.0.0.1";
    final static int PORT =8080;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Client request");

            String response = in.readLine();
            System.out.println("Client:" + response);


        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            socket = null;
        }



    }

}
