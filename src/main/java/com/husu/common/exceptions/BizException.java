package com.husu.common.exceptions;

import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.ClientBusinessCodeEnum;

/**
 * 业务异常
 *
 * @author husu
 */
public class BizException extends BaseException {
    private static final long serialVersionUID = -6266472762086999418L;

    public BizException() {
        super();
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public BizException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public BizException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public BizException(BusinessCode code, Throwable cause) {
        super(cause);
        this.businessCode = code;
    }


    public BizException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String message) {
        super(message);
        super.setCode(code);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public BizException(Throwable cause) {
        super(cause);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public BizException(String code, String message, Throwable cause) {
        super(message, cause);
        super.setCode(code);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }
}