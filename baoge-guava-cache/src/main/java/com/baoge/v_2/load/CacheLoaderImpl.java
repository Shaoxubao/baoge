package com.baoge.v_2.load;

import com.alibaba.fastjson.JSONArray;
import com.baoge.common.CacheConstants;
import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CacheLoaderImpl extends CacheLoader {

    private JedisCommands dataSource;

    @Value("${cache.monitor.on:false}")
    private boolean openMoniotr;

    private CommandFactory commandFactory = new CommandFactory();
    public static Map<String, String> KEY_METHOD_REF = new ConcurrentHashMap<>();//<key_prefix, method_type>
    public static Map<String, String> KEY_METHOD_TMP = new ConcurrentHashMap<>();
    public static Map<Object, String> KEY_TO_BE_REMOVED = new ConcurrentHashMap<>();

    public void setDataSource(JedisCommands dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object load(Object args) {
        try {
            String key = (String) args;
            String methodType = getMethodType(key);
            if (methodType == null) return null;

            log.debug(String.format("++++load [%s] from redis", key));
            Object result = commandFactory.getCommand(methodType).query(key);
            if (result == null) {
                KEY_TO_BE_REMOVED.put(key, CacheConstants.STRING_CACHE_NULL);
                return CacheConstants.CACHE_NULL;
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            return CacheConstants.CACHE_NULL;
        }
    }

    public String getMethodType(String key) {
        String key_prefix = getKeyPrefix(key);
        if (key_prefix == null) return null;
        String method_type = KEY_METHOD_REF.get(key_prefix);
        return method_type == null ? KEY_METHOD_TMP.get(key_prefix) : method_type;
    }

    public static String getKeyPrefix(String key) {
        if (key == null || "".equals(key.trim())) return null;
        int index = key.indexOf(CacheConstants.KEY_SEPARATE_FLAG);
        return index == -1 ? getPrefixByKey(key) : key.substring(0, index + 1);
    }

    public static String getPrefixByKey(String key) {
        for (String str : KEY_METHOD_TMP.keySet()) {
            if (key.startsWith(str)) return str;
        }
        return null;
    }

    class CommandFactory {
        public AbstractCommand getCommand(String type) {
            switch (type) {
                case CacheConstants.REDIS_METHOD_GET:
                    return new Get();
                case CacheConstants.REDIS_METHOD_HVALS:
                    return new Hvals();
                case CacheConstants.REDIS_METHOD_HGETALL:
                    return new HgetAll();
                case CacheConstants.REDIS_METHOD_HKEYS:
                    return new Hkeys();
                case CacheConstants.REDIS_METHOD_HGET:
                    return new Hget();
                case CacheConstants.REDIS_METHOD_ZRANGE:
                    return new Zrange();
                case CacheConstants.REDIS_METHOD_SMEMBERS:
                    return new Smembers();
                case CacheConstants.REDIS_METHOD_ZREVRANGE:
                    return new Zrevrange();
                case CacheConstants.REDIS_METHOD_ZRANGEWITHSCORE:
                    return new ZrangeWithScores();
                case CacheConstants.REDIS_METHOD_ZCARD:
                    return new Zcard();
                case CacheConstants.REDIS_METHOD_LRANGE:
                    return new Lrange();
                case CacheConstants.REDIS_METHOD_HMGET:
                    return new Hmget();
                default:
                    return null;
            }
        }
    }

    abstract class AbstractCommand<R> {
        protected abstract R doQuery(String... key);

        protected R query(String key) {
            if (!check(key)) return null;
            return doQuery(convert(key));
        }

        protected boolean check(String key) {
            if (StringUtils.isEmpty(key)) return false;
            return true;
        }

        protected String[] convert(String key) {
            return key.split(CacheConstants.FLAG);
        }
    }

    class Get extends AbstractCommand<String> {
        @Override
        protected String doQuery(String... key) {
            return dataSource.get(key[1]);
        }
    }

    class Hvals extends AbstractCommand<List<String>> {
        @Override
        protected List<String> doQuery(String... key) {
            return dataSource.hvals(key[1]);
        }
    }

    class HgetAll extends AbstractCommand<Map<String, String>> {
        @Override
        protected Map<String, String> doQuery(String... key) {
            return dataSource.hgetAll(key[1]);
        }
    }

    class Hkeys extends AbstractCommand<Set<String>> {
        @Override
        protected Set<String> doQuery(String... key) {
            return dataSource.hkeys(key[1]);
        }
    }

    class Hget extends AbstractCommand<String> {
        @Override
        protected String doQuery(String... key) {
            return dataSource.hget(key[1], key[2]);
        }
    }

    class Smembers extends AbstractCommand<Set<String>> {
        @Override
        protected Set<String> doQuery(String... key) {
            return dataSource.smembers(key[1]);
        }
    }

    class Zrange extends AbstractCommand<Set<String>> {
        @Override
        protected Set<String> doQuery(String... key) {
            return dataSource.zrange(key[1], Long.parseLong(key[2]), Long.parseLong(key[3]));
        }
    }

    class Zrevrange extends AbstractCommand<Set<String>> {
        @Override
        protected Set<String> doQuery(String... key) {
            return dataSource.zrevrange(key[1], Long.parseLong(key[2]), Long.parseLong(key[3]));
        }
    }

    class ZrangeWithScores extends AbstractCommand<Set<Tuple>> {
        @Override
        protected Set<Tuple> doQuery(String... key) {
            return dataSource.zrangeWithScores(key[1], Long.parseLong(key[2]), Long.parseLong(key[3]));
        }
    }

    class Zcard extends AbstractCommand<Long> {
        @Override
        protected Long doQuery(String... key) {
            return dataSource.zcard(key[1]);
        }
    }

    class Lrange extends AbstractCommand<List<String>> {
        @Override
        protected List<String> doQuery(String... key) {
            return dataSource.lrange(key[1], Long.parseLong(key[2]), Long.parseLong(key[3]));
        }
    }

    class Hmget extends AbstractCommand<List<String>> {
        @Override
        protected List<String> doQuery(String... key) {
            JSONArray jsonArray = JSONArray.parseArray(key[2]);
            String[] args = new String[jsonArray.size()];
            jsonArray.toArray(args);
            return dataSource.hmget(key[1], args);
        }
    }
}
