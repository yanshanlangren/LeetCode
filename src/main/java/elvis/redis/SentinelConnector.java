package elvis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SentinelConnector {
    public static void getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<String> sentinels = new HashSet<String>(Arrays.asList(
                "192.168.0.2:26379",
                "192.168.0.3:26379",
                "192.168.0.4:26379"
        ));
        JedisSentinelPool pool = new JedisSentinelPool("cl-y90ec29c", sentinels, jedisPoolConfig, 5000);
        Jedis jedis = pool.getResource();
        jedis.set("hello", "world");
        System.out.println(jedis.get("hello"));
    }

    public static void main(String[] args){
        getJedisPool();
    }
}
