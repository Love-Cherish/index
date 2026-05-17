package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现类
 * 实现业务逻辑，调用 Repository 层进行数据访问
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> getUsersByAgeRange(Integer minAge, Integer maxAge) {
        return new ArrayList<>();
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }
}
