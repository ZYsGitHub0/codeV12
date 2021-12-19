package com.happy233.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zy136
 * @date 2021/12/19 12:02
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
