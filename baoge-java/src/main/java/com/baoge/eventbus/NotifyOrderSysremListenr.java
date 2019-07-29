package com.baoge.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @Author shaoxubao
 * @Date 2019/7/29 11:51
 */

@Component
public class NotifyOrderSysremListenr {

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void register() {
        eventBus.register(this);
    }

    @Transactional
    @Subscribe
    @AllowConcurrentEvents
    public void asyncOrderInfo(final UpdateOrderEvent event) {

        System.out.println("asyncOrderInfo event:" + event.getOrderId() + " 进行中......");
    }
}
