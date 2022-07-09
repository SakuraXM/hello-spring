## Spring 缓存抽象
### 为不同的缓存提供一层抽象
* **为 Java 方法增加缓存，缓存执行结果**<br></br>
* **支持ConcurrentMap、EhCache、Caffeine、JCache(JSR-107)**<br></br>
* **接口**<br></br>
  * org.springframework.cache.Cache<br></br>
  * org.springframework.cache.CacheManager<br></br>


***


## 缓存应用
### 界面内部
* **长久不变或者一天里面不变的信息**
* **设置自动过期时间，过期后自动从数据库读取**

### Redis 中
* **在集群中设置缓存**
* **集群内部访问具有一致性要求**

### 不适用缓存
* **数据的读写别差距非常小**


***


## 基于注解的缓存
### @EnableCaching
* **@Cacheable**<br></br>
* **@CacheEvict**<br></br>
* **@CachePut**<br></br>
* **@Caching**<br></br>
* **@CacheConfig**<br></br>






***


## 参考链接
1. [https://blog.csdn.net/weixin_43767015/article/details/114524674](https://blog.csdn.net/weixin_43767015/article/details/114524674)