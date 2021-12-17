package com.happy233.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/17 20:07
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
