package org.example.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户仓储实现类
 * 实现 UserRepository 接口，使用 MyBatis-Plus 进行数据访问
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        userMapper.selectById(1);
        return userMapper.findAll();
    }

    @Override
    public Page<User> findAll(Page<User> page, User user) {
        // 2. 构建查询条件（按需加）
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        // 举例：只查询状态=1的用户
        wrapper.eq(User::getId, 1);
        // 举例：按ID倒序
        wrapper.orderByDesc(User::getId);
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User findById(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public int addUser(User user) {
        userMapper.insert(user);
        return 0;
    }

    @Override
    public int deleteUser(int userId) {
        userMapper.deleteById(userId);
        return 0;
    }
}
