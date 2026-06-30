package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.utils.RedisUtils;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器 - 提供用户查询接口
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;
    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public Result<List<User>> getAllUsers() {
        return Result.success(userRepository.findAll());
    }

    @PostMapping
    public void addUser(@Valid @RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable  Integer id) {
        return Result.success(userRepository.findById(id));
    }

    @GetMapping("/page/{current}/{size}")
    public IPage<User> getPageUsers(Page page, User user) {
        return userRepository.findAll(page, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable  Integer id){
        userService.deleteUser(id);
    }
}
