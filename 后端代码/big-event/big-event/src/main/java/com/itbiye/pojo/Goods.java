package com.itbiye.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Goods {
    @NotNull(groups = Category.Update.class)
    private Integer id;//主键ID
    @NotEmpty

    private String goodsName;//文章标题
    @NotNull
    private Integer warehouseId;//文章分类id
    private String state;//状态
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }

}
