## 使用说明
  本示例主要介绍了采用springboot + es 高级api 的方式，对es进行增删改查以及高亮的操作。所有的操作都可以在test包下找到相应的说明。  
  注意事项：  
* 1、需要在es中新建一个索引，相当于数据库。
* 2、spring-boot-starter-data-elasticsearch 依赖中，无法成功添加lucene-core 的包，因此，需要额外手动添加 lucence-core依赖。
同时，在spring-boot-starter-data-elasticsearch 中，排除掉 lucence-core 包。具体如下：
    ```xml
    <dependencies>
               <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-elasticsearch -->
               <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
                   <exclusions>
                       <exclusion>
                           <groupId>org.apache.lucene</groupId>
                           <artifactId>lucene-core</artifactId>
                       </exclusion>
                   </exclusions>
               </dependency>
               <dependency>
                   <groupId>org.projectlombok</groupId>
                   <artifactId>lombok</artifactId>
                   <version>1.18.6</version>
                   <scope>provided</scope>
               </dependency>
       
               <!-- https://mvnrepository.com/artifact/org.apache.lucene/lucene-core -->
               <dependency>
                   <groupId>org.apache.lucene</groupId>
                   <artifactId>lucene-core</artifactId>
                   <version>7.4.0</version>
               </dependency>
               <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
               <dependency>
                   <groupId>com.alibaba</groupId>
                   <artifactId>fastjson</artifactId>
                   <version>1.2.56</version>
               </dependency>
               <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-test</artifactId>
                   <scope>test</scope>
               </dependency>
           </dependencies>
           ```
* 3、新建项目的时候只要勾选core下面的lombok ，以及nosql下的es即可。
  
 