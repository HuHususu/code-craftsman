package com.husu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/8 0:27
 */
@Getter
@AllArgsConstructor
public enum SystemBusinessCodeEnum implements BusinessCode {
    /**
     * 系统执行出错, 一级宏观错误码
     */
    ERROR("0001");

    private final String sourceCode = "B";
    private final String errorCode;
    private final String errorMessage = "";
}
