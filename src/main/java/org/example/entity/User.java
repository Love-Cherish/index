package org.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("t_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;


    private String username;


    private String email;


    private String phone;


    private Integer age;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    // 默认构造函数
    public User() {
    }
}
