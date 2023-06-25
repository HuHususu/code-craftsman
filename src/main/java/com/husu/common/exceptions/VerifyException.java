package com.husu.common.exceptions;

import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.ClientBusinessCodeEnum;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/12 9:39
 */
public class VerifyException extends BaseException {
    private static final long serialVersionUID = -2047536176458629143L;

    public VerifyException() {
        super();
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public VerifyException(String message) {
        super(message);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public VerifyException(Throwable cause) {
        super(cause);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public VerifyException(String message, Throwable cause, String code,
                           Object[] values) {
        super(message, cause, code, values);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public VerifyException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public VerifyException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public VerifyException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }
}
