package com.stargazer.personal.esdemo.repository;

import com.stargazer.personal.esdemo.dto.UserDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:liumch@fosun.com">刘梦超</a>
 * @date 2019/3/11 20:22
 * @description: TODO
 * @modified: TODO
 * @version: 1.0
 **/
@Repository
public interface UserEsRepository extends ElasticsearchRepository<UserDto,String> {
    List<UserDto> queryUserDtoByName(String name);


}
