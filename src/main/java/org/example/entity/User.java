package org.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
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

    @NotNull()
    private String username;

    @NotNull()
    private String email;

    @NotNull()
    private String phone;

    @NotNull()
    private Integer age;

    private String password="123456";

    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    // 默认构造函数
    public User() {
    }
}
