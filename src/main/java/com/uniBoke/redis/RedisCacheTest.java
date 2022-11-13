package com.uniBoke.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author lzn
 *
 * TimeUnit.DAYS         日的工具类
 * TimeUnit.HOURS        时的工具类
 * TimeUnit.MINUTES      分的工具类
 * TimeUnit.SECONDS      秒的工具类
 * TimeUnit.MILLISECONDS 毫秒的工具类
 * 类说明
 */
@Component("redisCache")
public class RedisCacheTest{

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 切换数据库：
     *          0：存放用户注册的验证码
     *          1：存放用户找回密码的验证码
     *          2：存放用户登录验证码
     * */
    public void selectDb(int index){
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(index);
    }

    /**
     * 向Hash中添加值
     * @param key      可以对应数据库中的表名
     * @param field    可以对应数据库表中的唯一索引
     * @param value    存入redis中的值
     */
    public void hset(String key, String field, String value) {
        if(key == null || "".equals(key)){
            return ;
        }
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 从redis中取出值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field){
        if(key == null || "".equals(key)){
            return null;
        }
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 判断 是否存在 key 以及 hash key
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field){
        if(key == null || "".equals(key)){
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 查询 key中对应多少条数据
     * @param key
     * @return
     */
    public long hsize(String key) {
        if(key == null || "".equals(key)){
            return 0L;
        }
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 删除
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        if(key == null || "".equals(key)){
            return;
        }
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 插入String中的键值
     * */
    public void set(String key,String value,Long time) throws Exception {
        if(key == null || "".equals(key)){
            return;
        }
        try{
            redisTemplate.opsForValue().set(key, value,time, TimeUnit.SECONDS);
        }catch (Exception e){
            throw new Exception("redis插入失败",e);
        }
    }
    /**
     * 插入String中的键值
     * */
    public String get(String key){
        if(key == null || "".equals(key)) {
            return "建明为空！！";
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     *获取key的过期时间
     * */
    public Long ttl(String key){
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }
}