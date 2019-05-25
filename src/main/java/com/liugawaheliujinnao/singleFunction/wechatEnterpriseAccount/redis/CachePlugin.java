package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: redis cache API<br/>
 * redis缓存服务调用API
 *
 * @author LiugawaHeLiujinnao
 * @version 0.0.1
 */
public interface CachePlugin {

    /**
     * KEYS pattern<br/>
     * 查找所有符合给定模式 pattern 的 key 。<br/>
     * KEYS * 匹配数据库中所有 key 。<br/>
     * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。<br/>
     * KEYS h*llo 匹配 hllo 和 heeeeello 等。<br/>
     * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。<br/>
     * 特殊符号用 \ 隔开<br/>
     *
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);

    /**
     * HMSET key field value [field value ...]<br/>
     * 同时将多个 field -value (域-值)对设置到哈希表 key 中。 <br/>
     * 此命令会覆盖哈希表中已存在的域。 <br/>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param hash
     * @return
     */
    String addHash(String key, Map<String, String> hash);

    /**
     * HMSET key field value [field value ...]<br/>
     * 同时将多个 field -value (域-值)对设置到哈希表 key 中。 <br/>
     * 此命令会覆盖哈希表中已存在的域。 <br/>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param hash
     * @param seconds
     * @return
     */
    String addHash(String key, Map<String, String> hash, int seconds);

    /**
     * SET key value [EX seconds] [PX milliseconds] [NX|XX]<br/>
     * 将字符串值 value 关联到 key 。<br/>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。<br/>
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * SET key value [EX seconds] [PX milliseconds] [NX|XX]<br/>
     * 将字符串值 value 关联到 key 。<br/>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。<br/>
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    String set(String key, String value, int seconds);

    /**
     * HMGET key field [field ...]<br/>
     * 返回哈希表 key 中，一个或多个给定域的值。<br/>
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。<br/>
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。<br/>
     *
     * @param key
     * @return
     */
    Map<String, String> getHash(String key);

    /**
     * GET key<br/>
     * 返回 key 所关联的字符串值。<br/>
     * 如果 key 不存在那么返回特殊值 nil 。<br/>
     * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。<br/>
     *
     * @param key
     * @return
     */
    String get(String key);

    Long getLong(String key);

    /**
     * HGET KEY_NAME FIELD_NAME<br/>
     * 返回 KEY_NAME 对应的map中字段 FIELD_NAME 所关联的字符串值。<br/>
     * 如果 key 不存在那么返回特殊值 0 。<br/>
     *
     * @param keyName
     * @param fieldName
     * @return
     */
    Long hgetLong(String keyName, String fieldName);

    /**
     * DEL key [key ...]<br/>
     * 删除给定的一个或多个 key 。<br/>
     * 不存在的 key 会被忽略。<br/>
     *
     * @param key
     * @return
     */
    Long deleteKey(String key);

    /**
     * EXISTS key<br/>
     * 检查给定 key 是否存在。<br/>
     *
     * @param key
     * @return
     */
    Boolean existsKey(String key);

    /**
     * HINCRBY key field increment<br/>
     * 为哈希表 key 中的域 field 的值加上增量 increment 。<br/>
     * 增量也可以为负数，相当于对给定域进行减法操作。<br/>
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br/>
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br/>
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。<br/>
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。 <br/>
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hincrBy(String key, String field, long value);

    /**
     * HSET key field value<br/>
     * 将哈希表 key 中的域 field 的值设为 value 。<br/>
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。<br/>
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。<br/>
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     * @return
     */
    Long addHash(String key, String field, String value, Integer expire);

    /**
     * HGET key field<br/>
     * 返回哈希表 key 中给定域 field 的值。<br/>
     *
     * @param key
     * @param field
     * @return
     */
    String getHashValue(String key, String field);

    /**
     * HGET key field<br/>
     * 返回哈希表 key 中给定域 field 的值。<br/>
     *
     * @param key
     * @param field
     * @return
     */
    Long getHashLongValue(String key, String field);

    /**
     * SADD key member [member ...]<br/>
     * 将一个member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br/>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br/>
     * 当 key 不是集合类型时，返回一个错误。<br/>
     *
     * @param key
     * @param val
     * @param expire
     * @return
     */
    Long addMember(String key, String val, Integer expire);

    /**
     * 使用管道通信批量将多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br/>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br/>
     * 当 key 不是集合类型时，返回一个错误。<br/>
     *
     * @param key
     * @param members
     * @param expire
     */
    void addLongMembers(String key, List<Long> members, Integer expire);
    List<Long> getLongMembers(String key);

    /**
     * SMEMBERS key<br/>
     * 返回集合 key 中的所有成员。<br/>
     * 不存在的 key 被视为空集合。<br/>
     *
     * @param key
     * @return
     */
    List<String> getMembers(String key);

    void deleteAll(String prefix);

    /**删除hash中的指定field的值**/
    Long delHashField(String key, String field);

    void addMembers(String key, List<String> members, Integer expire);
    /**批量操作根据多个hash键获取多个map**/
    List<Map<String,String>> getHashs(List<String> keys);
}
