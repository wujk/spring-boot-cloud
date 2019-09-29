# **spring-boot-redis**

1、 引入jar包
-
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-cache</artifactId>
     </dependency>
     <!-- 使用redis作为缓存默认，spring使用ConcurrentMapCache -->
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-redis</artifactId>
     </dependency>

    
2、数据库配置
-
    spring.redis.host=192.168.140.215
    spring.redis.port=6379
    spring.redis.password=pass
    spring.redis.database=3
    spring.redis.jedis.pool.max-active=8
    spring.redis.jedis.pool.max-wait=-1
    spring.redis.jedis.pool.max-idle=500
    spring.redis.jedis.pool.min-idle=0
    spring.redis.timeout=300  #报错Command timed out after no timeout,修改application.properties文件的redis连接超时时间，修改到200或以上
    
3、开启缓存
-
    @SpringBootApplication
    @EnableCaching
    public class SpringBootCacheApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringBootCacheApplication.class, args);
        }
    
    }
    
4、使用缓存
-
    @RestController
    public class CacheController {
    
        @Autowired
        IBeanDao beanDao;
    
        @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
        @Cacheable("cache")
        public Bean getBean(@PathVariable("id") Integer id) {
            return beanDao.getBean(id);
        }
    }
    

- Cache	缓存接口，定义缓存操作。实现有：RedisCache、EhCacheCache、ConcurrentMapCache等
- CacheManager	缓存管理器，管理各种缓存（cache）组件

5、缓存主要注解
-
    @Cacheable	主要针对方法配置，能够根据方法的请求参数对其进行缓存
    @CacheEvict	清空缓存
    @CachePut	保证方法被调用，又希望结果被缓存。
    与@Cacheable区别在于是否每次都调用方法，常用于更新
    @EnableCaching	开启基于注解的缓存
    keyGenerator	缓存数据时key生成策略
    serialize	缓存数据时value序列化策略
    @CacheConfig	统一配置本类的缓存注解的属性
    
