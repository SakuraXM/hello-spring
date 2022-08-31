## 与Redis建立连接
### 配置连接工厂
* **LettuceConnectionFactory 与 JedisConnectionFactory** <br></br>
  * **RedisStandaloneConfiguration** <br></br>
  * **RedisSentinelConfiguration** <br></br>
  * **RedisClusterConfiguration**

***

## 读写分离
### Lettuce 内置支持读写分离
* **只读主、只读从** <br></br>
* **优先读主、优先读从**

### LettuceClientConfiguration
### LettucePoolingClientConfiguration
### LettuceClientConfigurationBuilderCustomizer

***

## RedisTemplate
### RedisTemplate<K, V>
* opsForXxx()

### StringRedisTemplate

### <font color=red>**注：一定要设置过期时间**</font>

### RedisTemplate操作方法
![1.png](images/1.png)
![2.png](images/2.png)
![3.png](images/3.png)


***

## Redis Repository
#### 实体注解
* **@RedisHash** <br></br>
* **@Id** <br></br>
* **@Indexed**

***

## 处理不同类型数据源的 Repository
### 如何区分 Repository
* **根据实体的注解** <br></br>
* **根据继承的接口类型** <br></br>
* **扫描不同的包**
