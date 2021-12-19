package com.happy233.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zy136
 * @date 2021/12/19 15:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")//该Pojo对应的表名
public class User implements Serializable {

    //@TableId("id")//与表中的主键映射 如果表列名与pojo属性值不一致
    @TableId(type = IdType.AUTO)//指定主键生成策略
    private Integer id;
    //@TableField("username")//与表中的列映射 如果名称不一致
    private String username;//MybatisPlus自动开启驼峰映射所以表中是username也不影响Pojo属性映射
    private Date birthday;
    private String sex;
    private String address;

    @TableField(exist = false)//是否为数据库的字段
    private String xxxx;
}
