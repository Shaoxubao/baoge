package com.baoge.callback;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 16:59
 */

@Slf4j
@Data
public class Caller {

    private CallBackListener callBackListener;

    private Notifier notifier;

    private String question;


    public void call() {
        log.info("开始提问：");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifier.execute(Caller.this, question);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        log.info("提问完毕，我去干其他事了");
    }

}
