package com.kj.commdityinfo.security.utils;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * 关于Jedis操作对象的工具类
 * @author kj
 * @Date 2020/3/17 15:59
 * @Version 1.0
 */
public class JedisUtils {

    /**
     * 在redis中锁对应的名称  减法时  lock_item(id)_sub
     * 在redis中对应的Item数量 item(id)
     * 在redis中对应的每个分类首页的缓存 cache_cate(id)_index_page
     */


    //默认的在redis中存放的时间(一天)
    public static final Integer TIME = 60 * 60 * 24;

    //分布式锁对应存放的时间
    public static  final Integer LOCKTIME = 3;



    private static JedisPool jedisPool = null;
    private static JedisPoolConfig jedisPoolConfig = null;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        int maxTotal = Integer.parseInt(resourceBundle.getString("redis.pool.maxTotal"));
        int maxIdle = Integer.parseInt(resourceBundle.getString("redis.pool.maxIdle"));
        long maxWait = Long.parseLong(resourceBundle.getString("redis.pool.maxWait"));
        String ip = resourceBundle.getString("redis.ip");
        int port = Integer.parseInt(resourceBundle.getString("redis.port"));

        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);

        jedisPool = new JedisPool(jedisPoolConfig,ip,port);
    }

    public static String getCacheName(Integer cateId){
        return "cache_cate"+ cateId.intValue() +"_index_page";
    }

    //保存set
    public static void set(Object key, Object value){
        Jedis jedis = getResource();
//        //jedis.auth("kuangjie");
        jedis.set(SerializeUtils.serialize(key),
                SerializeUtils.serialize(value));
        jedis.close();
    }

    public static void setex(Object key, Integer seconds, Object value){
        Jedis jedis = getResource();
        jedis.setex(SerializeUtils.serialize(key),
                seconds,
                SerializeUtils.serialize(value));
        jedis.close();
    }

    public static Long setnx(String key, String value){
        Jedis jedis = getResource();
        Long setnx = jedis.setnx(key, value);
        jedis.close();
        return setnx;
    }

    public static void hmset(String key, Map<String, String> map){
        Jedis jedis = getResource();

        jedis.hmset(key,map);

        jedis.close();
    }

    public static Map<String, String> hmget(String key, String... fileds){
        Jedis jedis = getResource();
        List<String> result = jedis.hmget(key, fileds);
        jedis.close();
        if(result == null){
            System.out.println("redis中不存在此数据");
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        IntStream.range(0,fileds.length)
                .forEach(index->map.put(fileds[index], result.get(index)));
        return map;
    }

    //获取get
    public static Object get(Object key){
        Jedis jedis = getResource();
        byte[] bytes = jedis.get(SerializeUtils.serialize(key));
        jedis.close();
        if(bytes == null){
            System.out.println("redis中不存在此数据");
            return null;
        }
        return SerializeUtils.revSerialize(bytes);
    }

    public static void expire(String key, Integer integer){
        Jedis jedis = getResource();
        jedis.expire(key, integer);
        jedis.close();
    }


    public static void expire(Object key, Integer integer){
        Jedis jedis = getResource();
        jedis.expire(SerializeUtils.serialize(key), integer);
        jedis.close();
    }

    //判断key存不存在
    public static boolean exists(Object key){
        Jedis jedis = getResource();
        //jedis.auth("kuangjie");
        boolean flag = jedis.exists(SerializeUtils.serialize(key));
        jedis.close();
        return flag;
    }

    //删除del
    public static Object del(Object key){
        Jedis jedis = getResource();
        //jedis.auth("kuangjie");
        long l = jedis.del(SerializeUtils.serialize(key));
        jedis.close();
        return l;
    }

    //删除del
    public static Object del(String key){
        Jedis jedis = getResource();
        //jedis.auth("kuangjie");
        long l = jedis.del(key);
        jedis.close();
        return l;
    }

    //获取长度getsize
    public static long getSize(){
        Jedis jedis = getResource();
        //jedis.auth("kuangjie");
        long len = jedis.dbSize();
        jedis.close();
        return len;
    }

    public static void hincrby(String key, String field, Long value){
        Jedis jedis = getResource();
        jedis.hincrBy(key,field,value);
        jedis.close();
    }

    //清空clear
    public static void clear(){
        Jedis jedis = getResource();
        //jedis.auth("kuangjie");
        jedis.flushDB();
        jedis.close();
    }

    //获取连接对象Jedis
    public static Jedis getResource(){
        return jedisPool.getResource();
    }

    public static void main(String[] args) {

        JedisUtils.set("name","123");
        Object name = JedisUtils.get("name");
        System.out.println(name);
    }



}
