package com.husu.common.exceptions;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/25 9:33
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
