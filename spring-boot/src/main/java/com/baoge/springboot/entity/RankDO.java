package com.baoge.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author shaoxubao
 * @Date 2020/3/10 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankDO implements Serializable {

    private static final long serialVersionUID = -2945764297332470630L;

    /**
     * 排名
     */
    private Long rank;

    /**
     * 积分
     */
    private Float score;

    /**
     * 用户id
     */
    private Long userId;
}
