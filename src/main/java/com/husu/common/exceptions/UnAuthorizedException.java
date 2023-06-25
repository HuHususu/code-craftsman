package com.husu.common.exceptions;

import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.ClientBusinessCodeEnum;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 认证失败
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/21 17:28
 */
public class UnAuthorizedException extends BaseException {
    private static final long serialVersionUID = -1299648767996432231L;

    public UnAuthorizedException() {
        super(UNAUTHORIZED.getReasonPhrase());
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public UnAuthorizedException(String message) {
        super(message);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public UnAuthorizedException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public UnAuthorizedException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public UnAuthorizedException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }
}

