package com.husu.common.exceptions;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/21 17:09
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class StatusCodeNot2xxException extends BaseException {
    private final transient HttpRequest request;
    private final transient HttpResponse response;
    private final Integer httpStatusCode;

    public StatusCodeNot2xxException(HttpRequest request, HttpResponse response) {
        super(String.format("Request URL: 【%s】, Request Body: 【%s】HTTP Status: 【%s】, responseBody: 【%s】",
                request.getRequestLine().getUri(),
                request.getRequestLine().getClass(),
                response.getStatusLine().getStatusCode(),
                response.getStatusLine().getClass()));
        this.request = request;
        this.response = response;
        this.httpStatusCode = response.getStatusLine().getStatusCode();
    }
}

