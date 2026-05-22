package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.User;
import org.example.exception.ApplicationException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> getUsersByAgeRange(Integer minAge, Integer maxAge) throws ApplicationException {
        return new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) throws ApplicationException{
        userRepository.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) throws ApplicationException{
        userRepository.addUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) throws ApplicationException {
        userRepository.deleteUser(id);
    }
}
