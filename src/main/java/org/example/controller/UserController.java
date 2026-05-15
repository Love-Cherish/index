package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器 - 提供用户查询接口
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;
    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        redisUtils.get("test");
        try {
            List<User> users = userRepository.findAll();
            response.put("success", true);
            response.put("data", users);
            response.put("total", users.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/page/{current}/{size}")
    public IPage<User> getPageUsers(Page page, User user) {
        return userRepository.findAll(page, user);
    }

}
