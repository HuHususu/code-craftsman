package com.husu.common.log;

import com.husu.common.enums.BusinessPropertyEnum;
import com.husu.common.enums.TechniquePropertyEnum;
import org.slf4j.MDC;

/**
 * Logger Context
 *
 * @author husu
 */
public final class LoggerContext {
    private LoggerContext() {
    }


    public static void setHttpStatusCode(String httpStatusCode) {
        MDC.put(TechniquePropertyEnum.HTTP_STATUS_CODE.getLogFieldName(), httpStatusCode);
    }

    public static void setHttpUrl(String httpUrl) {
        MDC.put(TechniquePropertyEnum.HTTP_URL.getLogFieldName(), httpUrl);
    }

    public static void setHttpMethod(String httpMethod) {
        MDC.put(TechniquePropertyEnum.HTTP_METHOD.getLogFieldName(), httpMethod);
    }

    public static void setBusinessCode(String businessCode) {
        MDC.put(BusinessPropertyEnum.BUSINESS_CODE.getLogFieldName(), businessCode);
    }


    public static void clear() {
        MDC.clear();
    }
}
