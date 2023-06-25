package com.husu.common.exceptions;


import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.ThirdPartyBusinessCodeEnum;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * 请求的操作无法执行，当前请求之外的api或进程进行交互
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/21 17:08
 */
public class UnProcessableEntityException extends BaseException {
    private static final long serialVersionUID = 3145727138553090166L;

    public UnProcessableEntityException() {
        super(UNPROCESSABLE_ENTITY.getReasonPhrase());
        this.businessCode = ThirdPartyBusinessCodeEnum.ERROR;
    }

    public UnProcessableEntityException(String message) {
        super(message);
        this.businessCode = ThirdPartyBusinessCodeEnum.ERROR;
    }

    public UnProcessableEntityException(Throwable cause) {
        super(cause);
        this.businessCode = ThirdPartyBusinessCodeEnum.ERROR;
    }

    public UnProcessableEntityException(String message, Throwable cause) {
        super(message, cause);
        this.businessCode = ThirdPartyBusinessCodeEnum.ERROR;
    }

    public UnProcessableEntityException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public UnProcessableEntityException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public UnProcessableEntityException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }
}

