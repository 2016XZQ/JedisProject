package black.tea.jedis.client;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClient {
    @Test
    public void jedisClient(){
        Jedis jedis = new Jedis("192.168.0.154",6379);

        jedis.select(1);
        jedis.set("what","fuck");
        String value = jedis.get("what");
        System.out.println(value);

        jedis.close();
    }
    @Test
    public void jedisPool(){
        JedisPool jedisPool = new JedisPool("192.168.0.154",6379);
        Jedis jedis = jedisPool.getResource();

        jedis.set("Are","you OK?");
        String str = jedis.get("hello");
        System.out.println(str);

        jedis.close();
        jedisPool.close();
    }
}
