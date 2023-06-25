package com.husu.common.exceptions;

import com.husu.common.enums.BusinessCode;
import com.husu.common.enums.ClientBusinessCodeEnum;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 未找到资源
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/21 17:07
 */
public class NotFoundException extends BaseException {

    private static final long serialVersionUID = -1299648767996432231L;

    public NotFoundException() {
        super(NOT_FOUND.getReasonPhrase());
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public NotFoundException(String message) {
        super(message);
        this.businessCode = ClientBusinessCodeEnum.ERROR;
    }

    public NotFoundException(BusinessCode businessCode) {
        super();
        this.businessCode = businessCode;
    }

    public NotFoundException(BusinessCode businessCode, String message) {
        super(message);
        this.businessCode = businessCode;
    }

    public NotFoundException(BusinessCode businessCode, String message, Throwable cause) {
        super(message, cause);
        this.businessCode = businessCode;
    }
}

