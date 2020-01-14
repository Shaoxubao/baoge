package com.baoge.netty000.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 14:47
 */
public class ChannelHandler {

    private Socket socket;

    private Charset charset;

    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        OutputStream out = null;

        try {
            out = socket.getOutputStream();
            out.write((msg.toString()).getBytes(charset));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket socket() {
        return socket;
    }


}
