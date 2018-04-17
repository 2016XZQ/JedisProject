package black.tea.jedis.client;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
    @Test
    public void jedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.154",7001));
        nodes.add(new HostAndPort("192.168.0.154",7002));
        nodes.add(new HostAndPort("192.168.0.154",7003));
        nodes.add(new HostAndPort("192.168.0.154",7004));
        nodes.add(new HostAndPort("192.168.0.154",7005));
        nodes.add(new HostAndPort("192.168.0.154",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("k3","333");
        String result = cluster.get("k3");
        System.out.println(result);
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
