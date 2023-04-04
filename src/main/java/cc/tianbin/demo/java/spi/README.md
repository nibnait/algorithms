> Service provider interface(SPl) is an APl intended to be implemented or extended by a third party.  
> It can be used to enable framework extension and replaceable components  

## Java SPI

> ServiceLoader.load(Log.class);  
> META-lNF/services/

源码阅读：[Main_Java_SPI.java](./javaSPI/Main_Java_SPI.java)

比如经典数据库驱动实例的获取 [JdbcDemo.java](./javaSPI/JdbcDemo.java)
1. 接口是什么：JDK定义的java.sql.Driver
2. 那实现类是什么呢？  
实现类是由不同的数据库厂商定义的MySQL Oracle Sql Server等
3. 约定优于配置的体现  
    META-lNF/services/java.sql.Driver 接口名称 里面写具体的实现类
4. 到底在哪里根据接口选择具体的实现类实例？  
   jdbc连接数据库，需要连接不同的数据库，获取到不同的 connection 
   因为不同的数据库需要使用的连接是不一样的，而这个 connection 的获取是根据 driver 的不同而得来的

总结：**其实就是根据一个接口，找不同的实现类实例[是可以自定义的，只需要按照约定优于配置的方式定义即可]**

## Dubbo SPI

> ExtensionLoader.getExtensionLoader(DistributedArchitecture.class)  
>  - META-lNF/dubbo/
>  - META-lNF/dubbo/internal
>  - META-lNF/services/ 

源码阅读: [Main_Dubbo_SPI.java](./dubboSPI/Main_Dubbo_SPI.java)

<https://cn.dubbo.apache.org/zh-cn/docsv2.7/dev/design/>

![](https://cn.dubbo.apache.org/imgs/dev/dubbo-framework.jpg)

配置文件，支持 kv配置。

## Spring SPI
> SpringFactoriesLoader.loadFactories(SpringXXXListener.class, classLoader);  
> META-lNF/spring.factories

源码阅读：[Main_Spring_SPI.java](./springSPI/Main_Spring_SPI.java)

业务代码，也可以实现一些 Spring 内部的接口，然后在 spring.factories 里添加下配置。即可被 spring 框架扫描到。
