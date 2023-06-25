package com.husu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 技术相关消息头枚举
 *
 * <p>
 * 1. 发送API级联调用时，传递业务Header，方便问题排查和网关消息检索
 * 2. 发送MQ时，用于消息Filter，指定不同的订阅者，可用于订阅者的网关请求头检索
 * 3. 记录Log时，用于统一属性&字段的名称，保持整体业务含义一致
 *
 * @author ricardo
 */
@Getter
@AllArgsConstructor
public enum TechniquePropertyEnum {
    /**
     * Http Status Code
     */
    HTTP_STATUS_CODE("httpStatusCode", "x-tpl-http-status-Code", "HttpStatusCode"),
    /**
     * HTTP URL
     */
    HTTP_URL("httpUrl", "x-tpl-http-url", "HttpUrl"),
    /**
     * HTTP Method
     */
    HTTP_METHOD("httpMethod", "x-tpl-http-method", "HttpMethod"),
    /**
     * 程序运行环境（gdev，staging，prd-e4，prd-e11）等
     */
    ENVIRONMENT("environment", "x-tpl-environment", "Environment"),
    /**
     * 消息队列的名称
     */
    MESSAGE_QUEUE("messageQueue", "x-tpl-message-queue", "MessageQueue"),

    /**
     * 消息队列的内容
     */
    MESSAGE_CONTENT("messageContent", "x-tpl-message-content", "MessageContent"),

    /**
     * 消息队列的内容
     */
    MESSAGE_HEADER("messageHeader", "x-tpl-message-header", "MessageHeader"),

    /**
     * 消息队列的消息唯一ID
     */
    MESSAGE_ID("messageId", "x-tpl-message-id", "MessageId");

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
}
