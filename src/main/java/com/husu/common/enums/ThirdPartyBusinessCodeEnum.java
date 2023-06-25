package com.husu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 调用第三方服务出错时使用的错误码
 *
 * @author ricardo
 */
@Getter
@AllArgsConstructor
public enum ThirdPartyBusinessCodeEnum implements BusinessCode {
    /**
     * 调用第三方服务出错, 一级宏观错误码
     */
    ERROR("0001");

    private final String sourceCode = "C";
    private final String errorCode;
    private final String errorMessage = "";
}
