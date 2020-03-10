package com.baoge.springboot.controller;

import com.baoge.springboot.entity.RankDO;
import com.baoge.springboot.redisrank.RankListComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/3/10 16:52
 *
 * redis 排行榜
 * 先初始化数据：
 * {@link com.baoge.springboot.rank.RankInitTest}
 */

@RestController
public class RedisRankController {

    @Autowired
    private RankListComponent rankListComponent;

    /**
     * 获取前n名的排行榜数据
     * http://localhost:8081/topn?n=10
     */
    @GetMapping(path = "/topn")
    public List<RankDO> showTopN(int n) {
        return rankListComponent.getTopNRanks(n);
    }

    @GetMapping(path = "/update")
    public RankDO updateScore(long userId, float score) {
        return rankListComponent.updateRank(userId, score);
    }

    /**
     * 获取用户的排行榜位置
     * http://localhost:8081/rank?userId=669
     */
    @GetMapping(path = "/rank")
    public RankDO queryRank(long userId) {
        return rankListComponent.getRank(userId);
    }

    /**
     * 获取用户所在排行榜的位置，以及排行榜中其前后n个用户的排行信息
     * http://localhost:8081/around?userId=669&n=3
     */
    @GetMapping(path = "/around")
    public List<RankDO> around(long userId, int n) {
        return rankListComponent.getRankAroundUser(userId, n);
    }

}
