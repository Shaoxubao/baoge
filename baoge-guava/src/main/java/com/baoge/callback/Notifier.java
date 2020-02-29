package com.baoge.callback;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 16:58
 */

@Slf4j
public class Notifier {

    public void execute(Caller caller, String msg) throws InterruptedException {

        log.info("收到消息=【{}】", msg);
        log.info("等待响应中。。。。。");

        TimeUnit.SECONDS.sleep(2);

        caller.getCallBackListener().callBackNotify("我在北京！");

    }

}
