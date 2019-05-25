package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Service
public class RedisCachePlugin implements CachePlugin {

    private static Log log = LogFactory.getLog(RedisCachePlugin.class);

    // 连接池
    private JedisPool pool;

    /**
     * 返回链接到连接池
     *
     * @param connect
     * @param isBroken
     */
    private void close(Jedis connect, boolean isBroken) {
        if (null != connect) {
            try {
                if (isBroken) {
                    pool.returnBrokenResource(connect);
                } else {
                    pool.returnResource(connect);
                }
            } catch (Exception ex) {
                log.error("jedis return resource to pool throw error :", ex);
            }
        }
    }

    /**
     * 从连接池获取链接
     *
     * @return
     */
    private Jedis getConnect() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } catch (Exception ex) {
            log.error("jedis get resource from pool throw error :", ex);
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
            }
        }
        return jedis;
    }

    @Override
    public Set<String> keys(String pattern) {
        Set<String> set = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            set = jedis.keys(pattern);
        } catch (Exception ex) {
            isBroken = true;
            ;
        } finally {
            this.close(jedis, isBroken);
        }
        return set;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = this.getConnect();
        Long result = 0l;
        Boolean isBroken = false;
        try {
            result = jedis.hincrBy(key, field, value);
        } catch (Exception ex) {
            isBroken = true;
            ;
        } finally {
            this.close(jedis, isBroken);
        }
        return result;
    }

    @Override
    public String addHash(String key, Map<String, String> hash) {
        String rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.hmset(key, hash);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public String addHash(String key, Map<String, String> hash, int seconds) {
        String rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.hmset(key, hash);
            jedis.expire(key, seconds);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public Map<String, String> getHash(String key) {
        Map<String, String> map = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            map = jedis.hgetAll(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return map;
    }

    @Override
    public Long deleteKey(String key) {
        Long num = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            num = jedis.del(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return num;
    }

    @Override
    public Boolean existsKey(String key) {
        Boolean isExists = false;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            isExists = jedis.exists(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return isExists;
    }

    @Override
    public String set(String key, String value) {
        String rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.set(key, value);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public String set(String key, String value, int seconds) {
        String rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.set(key, value);
            jedis.expire(key, seconds);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public String get(String key) {
        String rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.get(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public Long addHash(String key, String field, String value, Integer expire) {
        Long rows = 0L;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.hset(key, field, value);
            if (expire != null){
                jedis.expire(key, expire);
            }
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public String getHashValue(String key, String field) {
        String val = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            val = jedis.hget(key, field);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return val;
    }

    @Override
    public Long addMember(String key, String val, Integer expire) {
        Long num = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            num = jedis.sadd(key, val);
            jedis.expire(key, expire);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return num;
    }

    @Override
    public List<String> getMembers(String key) {
        List<String> members = new ArrayList<String>();
        Set<String> memberSet = null;
        Boolean isBroken = false;
        Jedis jedis = this.getConnect();
        try {
            memberSet = jedis.smembers(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        if (null != memberSet) {
            for (String member : memberSet) {
                members.add(member);
            }
        }
        return members;
    }

    @Override
    public void addLongMembers(String key, List<Long> members, Integer expire) {
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            // 建立管道
            Pipeline pipeline = jedis.pipelined();
            if (null != members && !members.isEmpty()) {
                for (Long member : members) {
                    pipeline.sadd(key, member.toString());
                }
                // 设置过期时间在设置值之后
                pipeline.expire(key, expire);
                // 异步执行
                pipeline.sync();
            }
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
    }

    @Override
    public void deleteAll(String prefix) {
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            Set<String> keys = jedis.keys(new StringBuffer().append(prefix).append("*").toString());
            if (null != keys && !keys.isEmpty()) {
                Pipeline pipeline = jedis.pipelined();
                for (String string : keys) {
                    pipeline.del(string);
                }
                pipeline.sync(); // 异步执行
            }
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
    }

    @Override
    public Long getLong(String key) {
        String value = null;
        Long longValue = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            value = jedis.get(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        try {
            longValue = Long.valueOf(value);
        } catch (Exception ex) {
            log.error("parse Long value error:", ex);
        }
        return longValue;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public Long hgetLong(String keyName, String fieldName) {
        String value = null;
        Long longValue = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            value = jedis.hget(keyName, fieldName);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        try {
            longValue = Long.valueOf(value);
        } catch (Exception ex) {
            longValue = 0L;
        }
        return longValue;
    }

    @Override
    public Long delHashField(String key, String field) {
        Long rows = null;
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            rows = jedis.hdel(key, field);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        return rows;
    }

    @Override
    public Long getHashLongValue(String key, String field) {
        Jedis jedis = this.getConnect();
        String val = null;
        Boolean isBroken = false;
        try {
            val = jedis.hget(key, field);
        } catch (Exception ex) {
            isBroken = true;
        }finally {
            this.close(jedis, isBroken);
        }
        if(val != null){
            return Long.valueOf(val);
        }else{
            return null;
        }
    }

    @Override
    public void addMembers(String key, List<String> members, Integer expire) {
        Jedis jedis = this.getConnect();
        Boolean isBroken = false;
        try {
            // 建立管道
            Pipeline pipeline = jedis.pipelined();
            if (null != members && !members.isEmpty()) {
                for (String member : members) {
                    pipeline.sadd(key, member);
                }
                pipeline.expire(key, expire);
                // 异步执行
                pipeline.sync();
            }
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
    }

    public List<Map<String,String>> getHashs(List<String> keys){
        Jedis jedis = this.getConnect();
        List<Map<String,String>> reslut = new ArrayList<Map<String,String>>();
        Boolean isBroken = false;
        try {
            List<Response<Map<String, String>>> res = new ArrayList<Response<Map<String, String>>>();
            Pipeline pipeline = jedis.pipelined();
            for (String key : keys) {
                Response<Map<String, String>> response = pipeline.hgetAll(key);
                res.add(response);
            }
            pipeline.sync();
            for (Response<Map<String, String>> response : res) {
                Map<String, String> map = response.get();
                reslut.add(map);
            }
        } catch (Exception ex) {
            isBroken = true;
        }finally {
            this.close(jedis, isBroken);
        }
        return reslut;
    }
    @Override
    public List<Long> getLongMembers(String key) {
        List<Long> members = new ArrayList<Long>();
        Set<String> memberSet = null;
        Boolean isBroken = false;
        Jedis jedis = this.getConnect();
        try {
            memberSet = jedis.smembers(key);
        } catch (Exception ex) {
            isBroken = true;
        } finally {
            this.close(jedis, isBroken);
        }
        if (null != memberSet) {
            for (String member : memberSet) {
                members.add(Long.valueOf(member));
            }
        }
        return members;
    }
}
