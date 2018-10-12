package com.blog.server.mapper;

import com.blog.api.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liumengwei
 * @date 2018/7/29 下午6:02
 */

@Mapper
@Component("userMapper")
public interface UserMapper {

    int deleteById(Integer id);

    int insert(User record);

    User findById(Integer id);

    int update(User record);

    List<User> findAll();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUserByUsernameAndPassword(User user);

}
