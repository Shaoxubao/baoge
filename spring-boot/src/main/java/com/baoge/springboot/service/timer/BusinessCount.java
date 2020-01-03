package com.baoge.springboot.service.timer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author shaoxubao
 * @Date 2019/12/26 15:55
 */

@Service
public class BusinessCount implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                try {
                    new CountThread().start();
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
        }, 1000, 1000);
    }

    class CountThread extends Thread {
        @Override
        public void run() {
            System.out.println("================");
        }
    }
}
