package com.itbiye.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author youmu
 */
@Data
public class Income {
    @NotNull(groups = Category.Update.class)
    private Integer id;  //主键
    @NotNull
    private Integer categoryId;//科目id
    @NotEmpty
    private String tradeName;//收入名称
    @NotEmpty
    private String profit;//收入金额
    private Integer createUser;//创建人id
    private String reMark;//备注
    private LocalDateTime updateTime;//添加时间
    private LocalDateTime createTime;//创建时间

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
