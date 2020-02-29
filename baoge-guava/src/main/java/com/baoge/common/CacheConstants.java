package com.baoge.common;

public interface CacheConstants {

    /**
     * 缓存节点在zk的路径
     */
    String PRIVDERS_PATH = "/providers/";

    byte KEYS_TO_BE_DELETE = 1;

    byte KEYS_TO_BE_ADD = 2;

    /**
     * 白名单存放路径(全量)
     */
    String WHITE_LIST_ALL_PATH = "/allKeys";

    String KEY_GROUP_CONFIG = "/new/%s/localCache/keyConfig";

    String NEW_ZK_ROOT_PATH = "/new/root";//第三方zk客户端根路径(配置相关都需要迁移到新zk客户端)

    String KEY_CONFIG = NEW_ZK_ROOT_PATH + "/localCache/keyConfig";

    String CACHE_DEFAULT = "cache_default";

    String CACHE_NIL = "cache_nil";//专用来储存空值

    String CACHE_NIL_VAL = "nil";//空值在缓存内的实际值

    String CACHE_TMP = "cache_tmp";

    String CACHE_ROOT_PATH = "/root/localCache/";

    String KEY_SEPARATE_FLAG = ".";

    String FLAG = "#";

    String FLAG_HGET = "hget#";

    String FLAG_ZRANGE = "zrange#";

    String FLAG_ZREVRANGE = "zrevrange#";

    String REDIS_METHOD_GET = "get";

    String REDIS_METHOD_HVALS = "hvals";

    String REDIS_METHOD_ZRANGE = "zrange";

    String REDIS_METHOD_HGETALL = "hgetAll";

    String REDIS_METHOD_HKEYS = "hkeys";

    String REDIS_METHOD_HGET = "hget";

    String REDIS_METHOD_SMEMBERS = "smembers";

    String REDIS_METHOD_ZREVRANGE = "zrevrange";

    String REDIS_METHOD_ZRANGEWITHSCORE = "zrangeWithScores";

    String REDIS_METHOD_ZCARD = "zcard";

    String REDIS_METHOD_LRANGE = "lrange";

    String REDIS_METHOD_HMGET = "hmget";

    String CACHE_NULL = "cache_null";

    String STRING_CACHE_NULL = "";

}
