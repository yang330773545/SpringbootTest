package com.example.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class Demo {

    // 先判空后判其他
    @Email(message = "用户名必须是邮箱")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min=6, max=25, message = "密码长度必须为6-25")
    private String password;

}
