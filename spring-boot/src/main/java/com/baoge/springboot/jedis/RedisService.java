package com.baoge.springboot.jedis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/3/10 10:09
 */

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取对象
     */
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 设置对象
     */
    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
            return true;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 删除
     */
    public boolean delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long ret = jedis.del(key);
            return ret > 0;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 判断key是否存在
     */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            closeJedis(jedis);
        }
    }


    /**
     * 关闭jedis
     */
    private void closeJedis(Jedis jedis) {//用完记得要关闭
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 将String转换为其他对象
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 将其他对象转换为String
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 增加值
     */
    public Long incrBy(final String key, final long integer) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incrBy(key, integer);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long incr(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 减少值
     */
    public <T> Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            closeJedis(jedis);
        }
    }

    // hset操作 ======================

    /**
     * 设置对象
     */
    public <T> boolean hset(String key, String field, T value) {
        Jedis jedis = null;
        try {
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }

            jedis = jedisPool.getResource();
            jedis.hset(key, field, str);
            return true;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 获取对象
     */
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
      * 获取对象
     */
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, String> map = jedis.hgetAll(key);
            return map;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 删除
     */
    public Long hdel(final String key, final String... fields) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hdel(key, fields);
        } finally {
            closeJedis(jedis);
        }
    }


    // zset操作 ======================

    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     */
    public boolean zadd(String key, double score, String member) {

        Jedis jedis = null;
        try {
            if (member == null || member.length() <= 0) {
                return false;
            }

            jedis = jedisPool.getResource();
            jedis.zadd(key, score, member);
            return true;
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 计算在有序集合中指定区间分数的成员数
     */
    public Long zcount(String key, double minScore, double maxScore) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zcount(key, minScore, maxScore);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 获取有序集合的成员数
     */
    public Long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zcard(key);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 有序集合中对指定成员的分数加上增量 increment
     */
    public Double zincrby(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zincrby(key, score, member);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员
     */
    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, start, end);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 返回有序集中指定区间内的成员，通过索引，分数从高到底
     */
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrange(key, start, end);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 返回有序集中指定分数区间内的成员，分数从高到低排序
     */
    public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            closeJedis(jedis);
        }
    }

    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
     */
    public Long zrevrank(final String key, final String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrank(key, member);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 移除有序集合中给定的分数区间的所有成员
     */
    public Long zremrangeByScore(final String key, final double start, final double end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long zremrangeByScore(final String key, final String start, final String end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 移除有序集合中的一个或多个成员
     */
    public Long zrem(final String key, final String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrem(key, members);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 移除有序集合中给定的字典区间的所有成员
     */
    public Long zremrangeByLex(final String key, final String min, final String max) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zremrangeByLex(key, min, max);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 移除有序集合中给定的排名区间的所有成员
     */
    public Long zremrangeByRank(final String key, final long start, final long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            closeJedis(jedis);
        }
    }

}
