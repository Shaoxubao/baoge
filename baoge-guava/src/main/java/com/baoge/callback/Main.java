package com.baoge.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 17:04
 */

@Slf4j
public class Main {

    public static void main(String[] args) {
        Caller caller = new Caller();

        caller.setNotifier(new Notifier());
        caller.setQuestion("你在哪儿？");
        caller.setCallBackListener(new CallBackListener() {
            @Override
            public void callBackNotify(String msg) {
                log.info("回复=【{}】", msg);
            }
        });

        caller.call();
    }

}
