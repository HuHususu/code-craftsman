package com.husu.home.model.po;

import lombok.Data;

/**
 * @author huyong(husu)
 * @date 2024/3/7 15:38
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Long createTime;
    private Long updateTime;
    private String createBy;
    private Long lastUpdateTime;
    private String lastUpdateBy;
    private Boolean deleted;
}
