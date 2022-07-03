## Spring MongoDB
### MongoDB是一款开源的文档型数据库
* **MongoDB是一个基于分布式文件存储的数据库。由C++语言编写。旨在为WEB应用提供可扩展的高性能数据存储解决方案**<br></br>
* **MongoDB是一个介于关系数据库和非关系数据库(nosql)之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的**<br></br>
* **官方网址**<br></br>
  * **https://www.mongodb.com**<br></br>
* **中文网址**<br></br>
  * **https://www.mongodb.org.cn**<br></br>

### Spring与MongoDB
* **Spring Data MongoDB**<br></br>
  * MongoTemplate<br></br>
  * Repository<br></br>

### 常用注解
* @Document<br></br>
* @Field<br></br>
* @Id<br></br>
  * import org.springframework.data.annotation.Id<br></br>

### MongoTemplate
* save / remove<br></br>
* Criteria / Query / Update <br></br>


***


## Docker辅助开发
### Docker for dev
* **简化了重复搭建开发环境的工作**<br></br>

### Docker for om
* **交付系统更为流畅**<br></br>
* **伸缩性更好**<br></br>

## Docker常用命令
### 镜像相关
* **`docker pull <image>`**<br></br>
* **`docker images`**<br></br>
* **`docker search <image>`**<br></br>

### 容器相关
* **`docker run`**<br></br>
* **`docker start/stop <容器名>`**<br></br>
* **`docker ps <容器名>`**<br></br>
* **`docker logs <容器名>`**<br></br>

### docker run 常用选项
#### docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
#### 选项说明
* **-d，后台运行容器**<br></br>
* **-e，设置环境变量**<br></br>
* **--expose / -p 宿主端口:容器端口**<br></br>
* **--name，指定容器名称**<br></br>
* **--link，链接不同容器**<br></br>
* **-v 宿主目录:容器目录，挂载磁盘卷**<br></br>

### Docker镜像配置
#### 官方Docker Hub
* **https://hub.docker.com**<br></br>

#### 官方镜像
* **镜像 https://www.docker-cn.com/registry-mirror** <br></br>
* **下载 https://www.docker-cn.com/get-docker** <br></br>

#### 阿里云镜像
* **https://dev.aliyun.com** <br></br>

#### 中文社区
* **https://www.docker.org.cn/index.html** <br></br>

#### Docker Engine
```
{
  "builder": {
    "gc": {
      "defaultKeepStorage": "20GB",
      "enabled": true
    }
  },
  "experimental": false,
  "features": {
    "buildkit": true
  },
  "registry-mirrors": [
    "https://******.mirror.aliyuncs.com",
    "http://hub-mirror.c.163.com"
  ]
}
```

### Docker启动MongoDB
#### 官方指引
* **https://hub.docker.com/_/mongo** <br></br>

#### 获取镜像
* **`docker pull mongo`**<br></br>

#### 运行MongoDB镜像
* `docker run --name mongo -p 27017:27017 -v ~/docker-data/mongo:/data/db -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo`<br></br>

#### 通过Docker登录到MongoDB容器
* **`docker exec -it mongo bash`**<br></br>

#### 通过Shell连接MongoDB
* **`mongo -u admin -p admin`**<br></br>


***


### 初始化MongoDB库及权限
#### Docker登录MongoDB
* **`docker exec -it mongo bash`**<br></br>

#### 通过Shell连接MongoDB
* **`mongo -u admin -p admin`**<br></br>

#### 显示数据库
* **`show dbs`**<br></br>

#### 创建库
* **`use springbucks`**<br></br>

#### 创建用户
```
db.createUser(
    {
        user: "springbucks",
        pwd: "springbucks",
        roles: [
            { role: "readWrite", db: "springbucks" }
        ]
    }
)
```

#### 显示用户
* **`show users`**<br></br>


***


### Spring MongoDB MongoCustomConversions
#### 自定义类型转换
* **MongoDataAutoConfiguration**<br></br>
  * **MongoTemplate**<br></br>
    * **`this.mongoConverter = mongoConverter == null ? getDefaultMongoConverter(mongoDbFactory) : mongoConverter;`**<br></br>
    * **`MongoCustomConversions conversions = new MongoCustomConversions(Collections.emptyList());`**<br></br>
* **MongoCustomConversions**
```
	@Bean
	@ConditionalOnMissingBean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Collections.emptyList());
	}
  ```



***


## 参考资料
1. [https://www.leftso.com/blog/268.html](https://www.leftso.com/blog/268.html)
2. [http://mp.weixin.qq.com/s?__biz=Mzg3MzAyODY2Nw==&mid=100000009&idx=1&sn=bcb5680973c15835be0abd50f4d290ff&chksm=4ee70c5d7990854bdcfca932d05aab2ba17acec4ef3224e29a593fcdcadd1981154638c3fad4#rd](http://mp.weixin.qq.com/s?__biz=Mzg3MzAyODY2Nw==&mid=100000009&idx=1&sn=bcb5680973c15835be0abd50f4d290ff&chksm=4ee70c5d7990854bdcfca932d05aab2ba17acec4ef3224e29a593fcdcadd1981154638c3fad4#rd)