package com.blog.server.service;

import com.blog.api.model.User;
import com.blog.api.service.UserService;
import com.blog.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author 言曌
 * @date 2018/7/29 下午6:16
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @GetMapping("/user")
    public List<User> listUsers() {
        return userMapper.findAll();
    }

    @Override
    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    @PutMapping("/user")
    public void updateUser(@RequestBody User user) {
        userMapper.update(user);
    }

    @Override
    @PostMapping("/user")
    public User insertUser(@RequestBody User user) {
        Integer userId = userMapper.insert(user);
        user.setId(userId);
        return user;
    }
}
