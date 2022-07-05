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
* **`JedisSentinelPool extends JedisPoolAbstract`**<br></br>

* **`JedisPoolAbstract extends Pool<Jedis>`**<br></br>

#### 初始化哨兵
1. **初始化哨兵并取得master，初始化master pool**
```
public JedisSentinelPool(String masterName, Set<HostAndPort> sentinels, GenericObjectPoolConfig<Jedis> poolConfig, JedisFactory factory, JedisClientConfig sentinelClientConfig) {
    super(poolConfig, factory);
    this.masterListeners = new HashSet();
    this.initPoolLock = new Object();
    this.poolConfig = poolConfig;
    this.factory = factory;
    this.sentinelClientConfig = sentinelClientConfig;
    HostAndPort master = this.initSentinels(sentinels, masterName);
    this.initMaster(master);
}
```

2. **遍历哨兵集合，取得哨兵的host和port，建立一个Jedis直接连接**
```
private HostAndPort initSentinels(Set<HostAndPort> sentinels, String masterName) {
    Jedis jedis = new Jedis(sentinel, this.sentinelClientConfig);
    ......
}
```

3. **通过Jedis发送一个请求，从哨兵里获取master地址的一个命令，取回一个master地址列表。
列表中一个是master的host，一个是master的port。初始化master的host和port后结束循环**
```
List<String> masterAddr = jedis.sentinelGetMasterAddrByName(masterName);
sentinelAvailable = true;
if (masterAddr != null && masterAddr.size() == 2) {
    master = this.toHostAndPort(masterAddr);
    log.debug("Found Redis master at {}", master);
    break;
}
```

4. **校验master是否非空。对哨兵的master建立一个 MasterListener 以便后面做切换**
```
if (master == null) {
    if (sentinelAvailable) {
        throw new JedisException("Can connect to sentinel, but " + masterName + " seems to be not monitored...");
    } else {
        throw new JedisConnectionException("All sentinels down, cannot determine where is " + masterName + " master is running...");
    }
} else {
    log.info("Redis master running at {}, starting Sentinel listeners...", master);
    var5 = sentinels.iterator();
    
    while(var5.hasNext()) {
        sentinel = (HostAndPort)var5.next();
        MasterListener masterListener = new MasterListener(masterName, sentinel.getHost(), sentinel.getPort());
        masterListener.setDaemon(true);
        this.masterListeners.add(masterListener);
        masterListener.start();
    }
    
    return master;
}
```


***


## Redis 集群模式
### Redis Cluster
* **数据自动分片（分成16384个 Hash Slot）**<br></br>

* **在部分节点失效时有一定可用性**<br></br>

### JedisCluster
* **Jedis 只从 Master 读数据，如果想要自动读写分离，可以定制**<br></br>
* **https://github.com/redis/jedis/pull/1789** <br></br>
* **https://github.com/redis/jedis/pull/1797** <br></br>

### BinaryJedisCluster

```
public BinaryJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig<Jedis> poolConfig) {
    this.connectionHandler = new JedisSlotBasedConnectionHandler(jedisClusterNode, poolConfig, connectionTimeout, soTimeout, user, password, clientName);
    this.maxAttempts = maxAttempts;
    this.maxTotalRetriesDuration = Duration.ofMillis((long)soTimeout * (long)maxAttempts);
}
```

### JedisClusterConnectionHandler
* **initializeSlotsCache**<br></br>
  * **完整构建出Slot和Master的信息**<br></br>
  * **initializeSlotsCache**
    * **this.cache.discoverClusterNodesAndSlots(jedis)**<br></br>
  * **getConnection()**<br></br>
    * **JedisSlotBasedConnectionHandler**<br></br>
      * **getConnection()**
```
public JedisClusterConnectionHandler(Set<HostAndPort> nodes, GenericObjectPoolConfig<Jedis> poolConfig, JedisClientConfig clientConfig) {
    this.cache = new JedisClusterInfoCache(poolConfig, clientConfig);
    this.initializeSlotsCache(nodes, clientConfig);
}
```

* **JedisSlotBasedConnectionHandler**
  * **随机取**<br></br>
    * **getConnection()**<br></br>
      * **getShuffledNodesPool**<br></br>
  * **通过具体的slot槽取**<br></br>
    * **getConnectionFromSlot**<br></br>

### JedisClusterInfoCache
1. **对每个cluster节点做一个连接池处理**
```
public JedisClusterInfoCache(GenericObjectPoolConfig<Jedis> poolConfig, JedisClientConfig clientConfig) {
    this.nodes = new HashMap();
    this.slots = new HashMap();
    this.rwl = new ReentrantReadWriteLock();
    this.r = this.rwl.readLock();
    this.w = this.rwl.writeLock();
    this.rediscoverLock = new ReentrantLock();
    this.poolConfig = poolConfig;
    this.clientConfig = clientConfig;
}
```
2. **发现整个集群节点和Slots信息。通过`List<Object> slots = jedis.clusterSlots();`
对整个Jedis取得clusterSlots信息，通过`List<Integer> slotNums = this.getAssignedSlotArray(slotInfo);`进行分配。
对整个节点和Slots做一个对应关系处理**
* **discoverClusterNodesAndSlots**
```
for(int i = 2; i < size; ++i) {
    List<Object> hostInfos = (List)slotInfo.get(i);
    if (!hostInfos.isEmpty()) {
        HostAndPort targetNode = this.generateHostAndPort(hostInfos);
        this.setupNodeIfNotExist(targetNode);
        if (i == 2) {
            this.assignSlotsToNode(slotNums, targetNode);
        }
    }
}
```


***


## 参考链接
1. [https://www.jianshu.com/p/5e4a1f92c88f](https://www.jianshu.com/p/5e4a1f92c88f)
2. [https://www.redis.com.cn/topics/sentinel.html](https://www.redis.com.cn/topics/sentinel.html)
3. [http://c.biancheng.net/redis/sentinel-model.html](http://c.biancheng.net/redis/sentinel-model.html)
4. [https://www.cnblogs.com/throwable/p/11601538.html](https://www.cnblogs.com/throwable/p/11601538.html)