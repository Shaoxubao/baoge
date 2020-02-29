package com.baoge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shaoxubao
 * @Date 2020/2/26 15:05
 */

@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(0, "请求成功"),
    FAILURE(1, "请求失败"),

    ;
    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
