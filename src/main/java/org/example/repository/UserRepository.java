package org.example.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.entity.User;

import java.util.List;

/**
 * 用户仓储接口
 * 定义数据访问的业务方法
 */
public interface UserRepository {
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAll();

    Page<User> findAll(Page<User> page, User user);

    int updateUser(User user);

    User findById(int userId);

    int addUser(User user);

    int deleteUser(int userId);
}
