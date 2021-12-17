package com.happy233.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/17 14:10
 */
@Data
@ConfigurationProperties(prefix = "init.man")//使用前缀 和配置文件中的init.man关联绑定
//@Component
public class Man {
    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private String[] address;
    private List<String> addressList;
    private Map<String, Object> hobbies;  // 爱好
    private Woman woman;
    private List<Woman> wifes;
}
