package com.blog.web.controller;

import com.blog.api.model.User;
import com.blog.api.service.UserService;
import com.blog.web.common.bean.ResultJson;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseEntity<ResultJson> login(@Param("username") String username, @Param("password") String password) {
        ResultJson resultJson = new ResultJson();
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        User u = userService.getUserByUsernameAndPassword(user);
        resultJson.setBody(u == null ? null : u);
        resultJson.setMsg(u == null ? "用户名或密码错误" : "登陆成功");
        return ResponseEntity.ok().body(resultJson);
    }

    @GetMapping("/user")
    public ResponseEntity<ResultJson> userList() {
        ResultJson resultJson = new ResultJson();
        List<User> userList = userService.listUsers();
        resultJson.setBody(userList);
        return ResponseEntity.ok().body(resultJson);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResultJson> userList(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        ResultJson resultJson = new ResultJson();
        resultJson.setBody(user);
        return ResponseEntity.ok().body(resultJson);
    }

    @PostMapping("/user")
    public ResponseEntity<ResultJson> insertUser(@RequestBody User user) {
        User result = null;
        ResultJson resultJson = new ResultJson();
        try {
            result = userService.insertUser(user);
        } catch (Exception e) {
            resultJson.setCode(ResultJson.ERROR);
            resultJson.setMsg("异常");
            return ResponseEntity.ok().body(resultJson);
        }
        resultJson.setBody(result);
        return ResponseEntity.ok().body(resultJson);
    }

    @PutMapping("/user")
    public ResponseEntity<ResultJson> updateUser(@RequestBody User user) {
        ResultJson resultJson = new ResultJson();
        try {
            if(user != null && user.getId() != null) {
                userService.updateUser(user);
            } else {
                resultJson.setCode(ResultJson.ERROR);
                resultJson.setMsg("失败");
                return ResponseEntity.ok().body(resultJson);
            }
        } catch (Exception e) {
            resultJson.setCode(ResultJson.ERROR);
            resultJson.setMsg("异常");
            return ResponseEntity.ok().body(resultJson);
        }
        return ResponseEntity.ok().body(resultJson);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ResultJson> deleteUser(@PathVariable("id") Integer id) {
        ResultJson resultJson = new ResultJson();
        try {
            userService.deleteUserById(id);
        } catch (Exception e) {
            resultJson.setCode(ResultJson.ERROR);
            resultJson.setMsg("异常");
            return ResponseEntity.ok().body(resultJson);
        }
        return ResponseEntity.ok().body(resultJson);
    }


}
