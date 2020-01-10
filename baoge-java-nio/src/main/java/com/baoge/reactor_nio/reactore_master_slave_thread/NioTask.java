package com.baoge.reactor_nio.reactore_master_slave_thread;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:38
 */
public class NioTask implements Serializable {

    private SocketChannel sc;
    private int op;
    private Object data;

    public NioTask(SocketChannel sc, int op) {
        this.sc = sc;
        this.op = op;
    }

    public NioTask(SocketChannel sc, int op, Object data) {
        this(sc, op);
        this.data = data;
    }
    public SocketChannel getSc() {
        return sc;
    }
    public void setSc(SocketChannel sc) {
        this.sc = sc;
    }
    public int getOp() {
        return op;
    }
    public void setOp(int op) {
        this.op = op;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
