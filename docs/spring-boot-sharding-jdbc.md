# **spring-boot-sharding-jdbc读写分离**

1、 引入jar包
-
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
         <groupId>org.mybatis.spring.boot</groupId>
         <artifactId>mybatis-spring-boot-starter</artifactId>
         <version>1.3.2</version>
     </dependency>

     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
     </dependency>

     <dependency>
         <groupId>com.alibaba</groupId>
         <artifactId>druid-spring-boot-starter</artifactId>
         <version>1.1.10</version>
     </dependency>

     <dependency>
         <groupId>io.shardingsphere</groupId>
         <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
         <version>3.1.0.M1</version>
     </dependency>
    
2、数据库配置(读写分离)
-
    sharding:
      jdbc:
        datasource:
          names: db-master,db-slave  # 名称之间不可以出现空格
          db-master: #org.apache.tomcat.jdbc.pool.DataSource
            type: com.alibaba.druid.pool.DruidDataSource
            driverClassName: com.mysql.jdbc.Driver
            url: jdbc:mysql://192.168.140.77:3306/cool?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false&serverTimezone=GMT
            username: root
            password: 123456
            #最大连接数
            maxPoolSize: 20
          db-slave: # 配置第一个从库
            type: com.alibaba.druid.pool.DruidDataSource
            driverClassName: com.mysql.jdbc.Driver
            url: jdbc:mysql://192.168.140.159:3306/cool?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT
            username: root
            password: 123456
            maxPoolSize: 20
        config:
          masterslave: # 配置读写分离
            load-balance-algorithm-type: round_robin # 配置从库选择策略，提供轮询与随机，这里选择用轮询//random 随机 //round_robin 轮询
            name: db1s1
            master-data-source-name: db-master
            slave-data-source-names: db-slave
        props:
          sql: # 开启SQL显示，默认值: false，注意：仅配置读写分离时不会打印日志！！！
            show: true
    
    mybatis:
      mapper-locations: classpath*:mybatis/*Mapper.xml
      type-aliases-package: com.wujk.springbootshardingjdbc
      
数据库配置(分表+读写分离) 用于分表的字段不能为空即使是自增长主键
-
    sharding:
      jdbc:
        datasource:
          names: db-master,db-slave  # 名称之间不可以出现空格
          db-master: #org.apache.tomcat.jdbc.pool.DataSource
            type: com.alibaba.druid.pool.DruidDataSource
            driverClassName: com.mysql.jdbc.Driver
            url: jdbc:mysql://192.168.140.77:3306/cool?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false&serverTimezone=GMT
            username: root
            password: 123456
            #最大连接数
            maxPoolSize: 20
          db-slave: # 配置第一个从库
            type: com.alibaba.druid.pool.DruidDataSource
            driverClassName: com.mysql.jdbc.Driver
            url: jdbc:mysql://192.168.140.159:3306/cool?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT
            username: root
            password: 123456
            maxPoolSize: 20
        config:
          sharding:
            tables:
              User:
                actual-data-nodes: ds_0.User_$->{0..3}
                table-strategy:
                  standard:
                    sharding-column: id
                    precise-algorithm-class-name: com.wujk.springbootshardingjdbc.MyPreciseShardingAlgorithm
            master-slave-rules:
              ds_0:
                master-data-source-name: db-master
                slave-data-source-names: db-slave
        props:
          sql: # 开启SQL显示，默认值: false，注意：仅配置读写分离时不会打印日志！！！
            show: true
    
    #sharding.jdbc.config.sharding.tables.user.actual-data-nodes: ds_0.User_$->{0..3}
    #sharding.jdbc.config.sharding.tables.user.table-strategy.standard.sharding-column: id
    #sharding.jdbc.config.sharding.tables.user.table-strategy.standard.precise-algorithm-class-name: com.wujk.springbootshardingjdbc.MyPreciseShardingAlgorithm
    #sharding.jdbc.config.sharding.tables.user.table-strategy.inline.sharding-column=id
    #sharding.jdbc.config.sharding.tables.user.table-strategy.inline.algorithm-expression=user_${id.longValue() % 4}
    
    #sharding.jdbc.config.sharding.master-slave-rules.ds_0.master-data-source-name: db-master
    #sharding.jdbc.config.sharding.master-slave-rules.ds_0.slave-data-source-names: db-slave
    
    mybatis:
      mapper-locations: classpath*:mybatis/*Mapper.xml
      type-aliases-package: com.wujk.springbootshardingjdbc
    
3、sql查询
-
    @Mapper
    public interface UserMapper {
    
        Integer insert(User user);
    
        List<User> list();
    
    }
    
4、controller
-
    @RestController
    public class ShardingJdbcController {
    
        @Autowired
        UserMapper userMapper;
    
        @PostMapping("insert")
        @Transactional
        public int insert(@RequestBody User user) {
            return userMapper.insert(user);
        }
    
        @GetMapping("list")
        public List<User> list() {
            return userMapper.list();
        }
    
    }
    
5、启动
-
    @SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
    public class SpringbootShardingJdncApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringbootShardingJdncApplication.class, args);
        }
    }