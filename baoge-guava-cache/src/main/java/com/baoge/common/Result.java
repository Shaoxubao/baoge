package com.baoge.common;

import com.baoge.enums.CodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author shaoxubao
 * @Date 2020/2/26 15:09
 */

@Data
public class Result<T> implements Serializable {

    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setMessage(CodeEnum.SUCCESS.getMessage());
        result.setData(data);

        return result;
    }

    public static <T> Result<T> failure(int code, String error) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(error);
        return result;
    }

    public static <T> Result<T> failure(CodeEnum errorCodeEnum) {
        Result<T> result = new Result<>();
        result.setCode(errorCodeEnum.getCode());
        result.setMessage(errorCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> failure() {
        Result<T> result = new Result<>();
        result.setCode(CodeEnum.FAILURE.getCode());
        result.setMessage(CodeEnum.FAILURE.getMessage());
        return result;
    }

}
