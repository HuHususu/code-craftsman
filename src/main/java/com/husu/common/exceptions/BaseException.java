package com.husu.common.exceptions;

import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.LogMonitorCategoryEnum;
import com.husu.common.enums.SystemBusinessCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/8 0:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private Object[] values;
    private String errorMsg;
    protected BusinessCode businessCode = SystemBusinessCodeEnum.ERROR;

    protected LogMonitorCategoryEnum monitorCategory = null;

    public BaseException() {
        super();
    }

    public BaseException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public BaseException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public BaseException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }


    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Throwable cause, String code,
                         Object[] values) {
        super(message, cause);
        this.code = code;
        this.values = values;
    }
}

