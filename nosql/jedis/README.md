## Spring Redis
### Redis 是一款开源的内存KV存储，支持多种数据结构
* **https://redis.io**<br></br>

### Spring 对 Redis 的支持
* **Spring Data Redis**<br></br>

  * **支持的客户端Jedis / Lettuce / Redisson**<br></br>
    * **https://redis.io/docs/clients/** <br></br>

  * **RedisTemplate**<br></br>

  * **Repository 支持**<br></br>

### Jedis 客户端使用
* **Jedis 不是线程安全的**<br></br>

* **通过 JedisPool 获得 Jedis 实例**<br></br>

* **直接使用 Jedis 中的方法**<br></br>


***


## Redis 哨兵模式
### Redis Sentinel 是 Redis 的一种高可用方案
* **监控、通知、自动故障转移、服务发现**<br></br>

```
       +----+
       | M1 |
       | S1 |
       +----+
          |
+----+    |    +----+
| R2 |----+----| R3 |
| S2 |         | S3 |
+----+         +----+

Configuration: quorum = 2
```

### JedisSentinelPool


***


## Redis 集群模式
### Redis Cluster
* **数据自动分片（分成16384个 Hash Slot）**<br></br>

* **在部分节点失效时有一定可用性**<br></br>

### JedisCluster
* **Jedis 只从 Master 读数据，如果想要自动读写分离，可以定制**<br></br>


***


## 参考链接
1. [https://www.jianshu.com/p/5e4a1f92c88f](https://www.jianshu.com/p/5e4a1f92c88f)
2. [https://www.redis.com.cn/topics/sentinel.html](https://www.redis.com.cn/topics/sentinel.html)
3. [http://c.biancheng.net/redis/sentinel-model.html](http://c.biancheng.net/redis/sentinel-model.html)