package com.blog.api.service;

import com.blog.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * user
 * @author liumengwei
 * @date 2018/10/12
 * @since V1.0
 */
@FeignClient(name = "cloud-producer")
public interface UserService {
    /**
     * 根据用户ID获得用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Integer id);

    /**
     * 获得所有的用户
     * @return
     */
    @GetMapping("/user")
    List<User> listUsers();

    /**
     * 根据用户ID删除用户
     * @param id
     */
    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable("id") Integer id);

    /**
     * 更新用户
     * @param user
     */
    @PutMapping("/user")
    void updateUser(@RequestBody User user);

    /**
     * 添加新用户
     * @param user
     */
    @PostMapping("/user")
    User insertUser(@RequestBody User user);

    @PostMapping("/login")
    User getUserByUsernameAndPassword(@RequestBody User user);
}

