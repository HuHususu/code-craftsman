package com.husu.common.exceptions;


import com.husu.common.enums.ClientBusinessCodeEnum;
import com.husu.common.enums.SystemBusinessCodeEnum;
import com.husu.common.log.LoggerContext;
import com.husu.common.model.GenericResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.*;


/**
 * 默认Exception Handler，Response=泛型的模式
 * <p>
 * logback异常优化
 * <a href="https://github.com/logfellow/logstash-logback-encoder/blob/main/src/main/java/net/logstash/logback/stacktrace/ShortenedThrowableConverter.java">...</a>
 * 由于405状态，还没有到达指定的Controller，因此统一采用泛型模式进行响应
 */
@Slf4j
@RestControllerAdvice
public final class GenericResponseExceptionHandler {
    private static final String BAD_REQUEST_LOG = "400:******Bad Request*******";
    private static final String NOT_FOUND_LOG = "404:******NOT_FOUND******";
    private static final String METHOD_NOT_ALLOWED_LOG = "405:******Method Not Allowed*******";
    private static final String INTERNAL_ERROR_LOG = "500:******未捕获的异常*******";
    private static final Pattern PATTERN = Pattern.compile("\\[\"\\S*\"]");

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(VerifyException.class)
    public GenericResponseEntity<String> handleVerifyException(VerifyException ex, HttpServletRequest request) {
        return generalExceptionHandler(
                BAD_REQUEST_LOG,
                ex,
                request,
                BAD_REQUEST,
                ex.getBusinessCode().getBusinessCodeString(),
                ex.getMessage());
    }

    /**
     * url 参数绑定成对象
     * body 参数类型错误 数据校验错误处理
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public GenericResponseEntity<String> badRequestException2(Exception ex, HttpServletRequest request) {
        GenericResponseEntity<String> result = generalExceptionHandler(
                BAD_REQUEST_LOG,
                ex,
                request,
                BAD_REQUEST,
                ClientBusinessCodeEnum.ERROR.getBusinessCodeString(),
                BAD_REQUEST.getReasonPhrase());

        BindingResult bindingResult = null;
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException c = (MethodArgumentTypeMismatchException) ex;
            result.setData("name:" + c.getName() + ';' + " " + "cause:" + c.getCause() + ';');
            return result;
        }
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
            bindingResult = c.getBindingResult();
        }
        if (ex instanceof BindException) {
            BindException c = (BindException) ex;
            bindingResult = c.getBindingResult();
        }
        if (bindingResult != null && bindingResult.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getField()).append(" ").append(fieldError.getDefaultMessage()).append(';');
            }
            result.setData(sb.toString());
        }

        return result;
    }

    /**
     * Body 参数类型不对
     * <p>
     * 这种类型的错误会直接提示后端序列化的细节，所以不能将错误信息直接暴露给前端
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public GenericResponseEntity<String> badUrlParRequestException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        GenericResponseEntity<String> result = generalExceptionHandler(
                BAD_REQUEST_LOG,
                ex,
                request,
                BAD_REQUEST,
                ClientBusinessCodeEnum.ERROR.getBusinessCodeString(),
                BAD_REQUEST.getReasonPhrase());

        if (StringUtils.isNotBlank(ex.getMessage())) {
            Matcher matcher = PATTERN.matcher(ex.getMessage());
            if (matcher.find()) {
                result.setMsg(String.format("%s is error type", matcher.group(0)));
            }
        }

        result.setData("Parameter Type Error");

        return result;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ValidationException.class, ServletRequestBindingException.class})
    public GenericResponseEntity<String> handleBadRequestException(Exception ex, HttpServletRequest request) {
        GenericResponseEntity<String> result = generalExceptionHandler(
                BAD_REQUEST_LOG,
                ex,
                request,
                BAD_REQUEST,
                ClientBusinessCodeEnum.ERROR.getBusinessCodeString(),
                BAD_REQUEST.getReasonPhrase());

        result.setData(ex.getMessage());

        return result;
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public GenericResponseEntity<String> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return generalExceptionHandler(
                NOT_FOUND_LOG,
                ex,
                request,
                NOT_FOUND,
                ex.getBusinessCode().getBusinessCodeString(),
                ex.getMessage());
    }

    /**
     * 请求方法类型不匹配，比如用GET请求了POST
     */
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public GenericResponseEntity<String> methodNotSupportedException(Exception ex, HttpServletRequest request) {
        return generalExceptionHandler(
                METHOD_NOT_ALLOWED_LOG,
                ex,
                request,
                METHOD_NOT_ALLOWED,
                ClientBusinessCodeEnum.ERROR.getBusinessCodeString(),
                ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BizException.class)
    public GenericResponseEntity<String> handleBizException(BizException ex, HttpServletRequest request) {
        return generalExceptionHandler(
                "",
                ex,
                request,
                CONFLICT,
                ex.getBusinessCode().getBusinessCodeString(),
                ex.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler({UnProcessableEntityException.class, StatusCodeNot2xxException.class})
    public GenericResponseEntity<String> handleUnProcessableException(BaseException ex, HttpServletRequest request) {
        return generalExceptionHandler(
                "",
                ex,
                request,
                UNPROCESSABLE_ENTITY,
                ex.getBusinessCode().getBusinessCodeString(),
                "Third-party Service Exception");
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationException.class)
    public GenericResponseEntity<String> handleApplicationException(Exception ex, HttpServletRequest request) {
        return generalExceptionHandler(
                INTERNAL_ERROR_LOG,
                ex,
                request,
                INTERNAL_SERVER_ERROR,
                SystemBusinessCodeEnum.ERROR.getBusinessCodeString(),
                "Application Exception");
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResponseEntity<String> handleAllExceptions(Exception ex, HttpServletRequest request) {
        return generalExceptionHandler(
                INTERNAL_ERROR_LOG,
                ex,
                request,
                INTERNAL_SERVER_ERROR,
                SystemBusinessCodeEnum.ERROR.getBusinessCodeString(),
                "Unknown Exception");
    }

    /**
     * 统一错误日志记录（标准化）
     *
     * @param prefix
     * @param ex
     */
    private void logError(String prefix, Exception ex) {
        String errorMessage = MessageFormat.format("prefix: {0}，error: {1}",
                StringUtils.isBlank(prefix) ? "N/A" : prefix,
                ex.getMessage());
        Throwable cause = ex.getCause() != null ? ex.getCause() : ex;

        if (ex instanceof BaseException) {
            BaseException bex = (BaseException) ex;
            if (bex.getMonitorCategory() != null) {
                log.error(errorMessage, cause);
                return;
            }
        }

        log.error(errorMessage, cause);
    }

    /**
     * 一般性异常处理
     * <p>
     * 1. 记录通用的日志字段
     * 2. 返回统一Response对象
     * 3. 标准化的异常日志记录
     *
     * @param errorPrefix
     * @param ex
     * @param request
     * @param httpStatus
     * @param businessCode
     * @param message
     * @return
     */
    @NotNull
    private GenericResponseEntity<String> generalExceptionHandler(
            String errorPrefix,
            Exception ex,
            HttpServletRequest request,
            HttpStatus httpStatus,
            String businessCode,
            String message
    ) {
        LoggerContext.setHttpUrl(request.getRequestURI());
        LoggerContext.setHttpMethod(request.getMethod());
        LoggerContext.setHttpStatusCode(String.valueOf(httpStatus.value()));
        LoggerContext.setBusinessCode(businessCode);

        GenericResponseEntity<String> result = new GenericResponseEntity<>(httpStatus.value(), message);

        logError(errorPrefix, ex);

        return result;
    }
}

