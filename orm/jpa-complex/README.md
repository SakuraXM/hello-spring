## 对象与关系的范式不匹配
![img.png](/images/img.png)

***

### Hibernate
* **一款开源的对象关系映射（Object/Relational Mapping）框架**<br><br/>
* **将开发者从95%的常见数据持久化工作中解放出来**<br><br/>
* **屏蔽了底层数据库的各种细节**

### 发展历程
* **2001年，Gavin King发布第一个版本**<br><br/>
* **2003年，Hibernate开发团队加入JBoss**<br><br/>
* **2006年，Hibernate3.2成为JPA实现**

***

## Java Persistence API
### JPA为对象关系映射提供了一种基于POJO的持久化模型
* **简化数据持久化代码的开发工作**<br><br/>
* **为Java社区屏蔽不同持久化API的差异**

2006年，JPA1.0作为JSR 220的一部分正式发布

***

## Spring Data
### 在保留底层存储特性的同时，提供相对一致的、基于Spring的编程模型
### 主要模块
* **Spring Data Commons**<br><br/>
* **Spring Data JDBC**<br><br/>
* **Spring Data JPA**<br><br/>
* **Spring Data Redis**<br><br/>
* **......**

***

## 常用JPA注解
### 实体类
* **@Entity**<br><br/>
* **@MappedSuperclass**<br><br/>
* **@Table(name)**

### 主键
* **@Id**<br><br/>
  * **@GeneratedValue(strategy, generator)**<br><br/>
  * **@SequenceGenerator(name, sequenceName)**

### 映射
* **@Column(name, nullable, length, insertable, updatable)**<br><br/>
* **@JoinTable(name)**<br><br/>
* **@JoinColumn(name)**

### 关系
* **@OneToOne**<br><br/>
* **@OneToMany**<br><br/>
* **@ManyToOne**<br><br/>
* **@ManyToMany**<br><br/>
* **@OrderBy**

### A simple example
    @Entity
    public class Person {
        @Id
        @GeneratedValue
        private Integer id;
    
        @Audited
        private String name;
    
        @Audited
        private String surname;
    
        @Audited
        @ManyToOne
        private Address address;
    }

***

## JDBC/JPA/Hibernate/MyBatis
* **JDBC是各种操作的基础**<br><br/>
* **JPA是个规范**<br><br/>
* **Hibernate是JPA的一种实现**<br><br/>
* **Spring Data JPA 用的是Hibernate**<br><br/>
* **MyBatis是另一种ORM框架**


***

## 参考链接
[https://docs.jboss.org/hibernate/orm/6.1/quickstart/html_single/](https://docs.jboss.org/hibernate/orm/6.1/quickstart/html_single/)