package com.baoge.eventbus;

import java.io.Serializable;

/**
 * @Author shaoxubao
 * @Date 2019/7/29 11:47
 */
public class UpdateOrderEvent implements Serializable {
    private static final long serialVersionUID = -5537187217066204665L;

    private Long orderId;

    public UpdateOrderEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
