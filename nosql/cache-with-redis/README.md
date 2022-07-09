## Spring 缓存抽象
### 为不同的缓存提供一层抽象
* **为 Java 方法增加缓存，缓存执行结果**<br></br>
* **支持ConcurrentMap、EhCache、Caffeine、JCache(JSR-107)**<br></br>
* **接口**<br></br>
  * org.springframework.cache.Cache<br></br>
  * org.springframework.cache.CacheManager<br></br>


***


## 缓存应用
### JVM内部
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


### spring.cache.cache-names 和 cacheNames
* **`spring.cache.cache-names=coffee`，用来配置默认缓存的，启动时会把其中指定的缓存创建出来，运行时的缓存不能超出这个指定的范围（与底层的缓存实现有关，比如Simple的不能超过，但Redis的可以），不配的话就看代码里用到哪些动态创建**<br></br>
* **@CacheConfig用来配置类级别共享的缓存配置，配置不在@CacheConfig里，就需要加在@Cacheable里**<br></br>

 
### proxyTargetClass=true
* **Spring基于Java接口的动态代理要求目标类得要实现接口，动态代理出来的类也是这个接口的实现**<br></br>
* **但往往很多时候，我们的类是没有实现什么接口的，故意去写一个接口也挺繁琐的**<br></br>
* **因此proxy-target-class让我们可以直接对类做代理**<br></br>

### proxyTargetClass=false
* **事实上，虽然没有配置proxyTargetClass，但Spring Boot自动配了**<br></br>
* **即使最后想尽一切办法设置成了false，Spring在判断Bean没有实现任何接口后，还是会设置为true**<br></br>
* **详见 ProxyProcessorSupport**<br></br>
* **Spring Boot issue**<br></br>
  * https://github.com/spring-projects/spring-boot/issues/12194
```
protected void evaluateProxyInterfaces(Class<?> beanClass, ProxyFactory proxyFactory) {
    Class<?>[] targetInterfaces = ClassUtils.getAllInterfacesForClass(beanClass, this.getProxyClassLoader());
    boolean hasReasonableProxyInterface = false;
    Class[] var5 = targetInterfaces;
    int var6 = targetInterfaces.length;

    int var7;
    Class ifc;
    for(var7 = 0; var7 < var6; ++var7) {
        ifc = var5[var7];
        if (!this.isConfigurationCallbackInterface(ifc) && !this.isInternalLanguageInterface(ifc) && ifc.getMethods().length > 0) {
            hasReasonableProxyInterface = true;
            break;
        }
    }

    if (hasReasonableProxyInterface) {
        var5 = targetInterfaces;
        var6 = targetInterfaces.length;

        for(var7 = 0; var7 < var6; ++var7) {
            ifc = var5[var7];
            proxyFactory.addInterface(ifc);
        }
    } else {
        proxyFactory.setProxyTargetClass(true);
    }

}
```



***


## 参考链接
1. [https://blog.csdn.net/weixin_43767015/article/details/114524674](https://blog.csdn.net/weixin_43767015/article/details/114524674)