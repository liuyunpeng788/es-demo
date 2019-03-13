package com.stargazer.personal.esdemo.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author <a href="mailto:liumch@fosun.com">刘梦超</a>
 * @date 2019/3/11 20:17
 * @description: TODO
 * @modified: TODO
 * @version:
**/

/**
 * @Document : 这个是ES的注解，在类使用，指定实体类的索引和类型。默认所有的属性都是索引的
 *             1、indexName ：　指定索引
 *             2、type：指定类型
 *             3、shards：指定分片的数量
 *             4、replicas：指定副本的数量
 *
 * @Field
 * 标注在属性上，用来指定属性的类型。其中的属性如下：
 * analyzer：指定分词器，es中默认使用的标准分词器，比如我们需要指定中文IK分词器，可以指定值为ik_max_word
 * type： 指定该属性在es中的类型，其中的值是FileType类型的值，比如FileType.Text类型对应es中的text类型
 * index：指定该词是否需要索引，默认为true
 * store：指定该属性内容是否需要存储，默认为
 * fielddata ：指定该属性能否进行排序，因为es中的text类型是不能进行排序（已经分词了）,可以不要
 * searchAnalyzer ： 指定搜索使用的分词器
 */

@Data
@Document(indexName = "es-test", type = "user", shards = 2, replicas = 0, refreshInterval = "-1")
public class UserDto {

    @Id
    private String id;

    @Field(type = FieldType.Keyword,store = true)
    private String name;

    @Field(type=FieldType.Integer,store = true)
    private Integer age; //年龄

    @Field( type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ",index = false)
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ",timezone="GMT+8")
    private Date birthday;

    @Field(type = FieldType.Text, store = true , analyzer = "index_ansj",searchAnalyzer = "search_ansj")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String description; //介绍


    public UserDto builder(String id, String name, Integer age,Date birthday,String description) {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setAge(age);
        dto.setBirthday(birthday);
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
