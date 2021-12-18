package com.happy233.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zy136
 * @date 2021/12/18 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    private Integer id;
    private String username;
    private Integer age;
}
