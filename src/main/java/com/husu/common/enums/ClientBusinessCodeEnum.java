package com.husu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户端错误时使用的错误码
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/12 9:43
 */
@Getter
@AllArgsConstructor
public enum ClientBusinessCodeEnum implements BusinessCode {
    ERROR("0001", StringUtils.EMPTY);
    private final String sourceCode = "A";
    private final String errorCode;
    private final String errorMessage;
}

