package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.User;

import java.util.List;

/**
 * 用户 Mapper 接口
 * 继承 BaseMapper 获得基础 CRUD 能力
 * 自定义 SQL 方法在此声明，在 XML 中实现
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
