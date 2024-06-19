package com.itbiye.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author youmu
 */
@Data
public class Admin {
    @NotNull
    private Integer id;//主键
    private String adminId;//管理员id
    @JsonIgnore
    private String password;//管理员密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String adminName;//管理员用户名
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

