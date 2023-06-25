package com.husu.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务消息头枚举
 * <p>
 * 1. 发送API级联调用时，传递业务Header，方便问题排查和网关消息检索
 * 2. 发送MQ时，用于消息Filter，指定不同的订阅者，可用于订阅者的网关请求头检索
 * 3. 记录Log时，用于统一属性&字段的名称，保持整体业务含义一致
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/12 9:43
 */
@Getter
@AllArgsConstructor
public enum BusinessPropertyEnum {
    /**
     * Business Code
     */
    BUSINESS_CODE("businessCode", "x-wms-business-code", "BusinessCode", "x-nemq-header-BusinessCode");


    /**
     * 用于编写Console日志时，业务字段的名称
     */
    private final String logFieldName;

    /**
     * 请求API时，透传的header
     */
    private final String apiHeaderName;

    /**
     * 发送MQ时，透传的header
     */
    private final String mqHeaderName;

    /**
     * Newegg Message Queue 包装后，透传到Subscriber的header
     * 前缀 (x-nemq-header)
     */
    private final String mqWrapperHeaderName;
}
