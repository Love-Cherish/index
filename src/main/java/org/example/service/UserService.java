package org.example.service;

import org.example.entity.User;
import org.example.exception.ApplicationException;

import java.util.List;

/**
 * 用户服务接口
 * 定义业务逻辑方法，不依赖 MyBatis-Plus
 */
public interface UserService {
    /**
     * 根据年龄范围查询用户
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 用户列表
     */
    List<User> getUsersByAgeRange(Integer minAge, Integer maxAge)throws ApplicationException;

    void updateUser(User user)throws ApplicationException;

    void addUser(User user)throws ApplicationException;


    void deleteUser(Integer id)throws ApplicationException;
}
