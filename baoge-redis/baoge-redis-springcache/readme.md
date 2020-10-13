### 一、为什么要用SpringCache？，它解决了什么问题？
    SpringCache是Spring3.1发版发布的，他是对使用缓存进行封装和抽象，通过在方法上使用annotation
    注解就能拿到缓存结果。正是因为用了annotation，所以他解决了业务代码和缓存代码的耦合问题，即在
    不入侵业务代码的基础上让现有代码即刻支持缓存，它让开发人员无感知的使用了缓存。
    （注意：对于redis的缓存，SpringCache只支持String，其他的List、Set、ZSet、Hash都不支持）
    
### 二、SpringCache相关注解
    @CacheConfig
    类级别的注解，统一该类的所有缓存的前缀。如：
    @CacheConfig(cacheNames = "user")
    public class UserServiceImpl implements UserService {
    
    @Cacheable(key = "#id")
    方法级别的注解，用于将方法的结果缓存起来，方法被调用时，先从缓存中
    读取数据，如果缓存中没有，再执行方法体，最后把返回值添加到缓存中。
    
    @CachePut(key = "#user.id")
    方法级别注解，用于更新缓存。如：
    @CachePut(key = "#user.id")
    @Override
    public User updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }
    
    @CacheEvict(key = "#id")
    方法级别注解，用于删除缓存。
 
### 三、Spring Cache注意事项
    对于redis的缓存，SpringCache只支持String，其他的List、Set、ZSet、Hash都不支持，
    对于List、Set、ZSet、Hash只能用RedisTemplate。
    对于多表操作不支持。