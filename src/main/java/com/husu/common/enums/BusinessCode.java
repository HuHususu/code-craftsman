package com.husu.common.enums;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/8 0:27
 */
public interface BusinessCode {
    /**
     * 业务领域编号
     */
    String DOMAIN_CODE = "1";

    /**
     * 获取错误产生来源
     *
     * @return A/B/C
     */
    String getSourceCode();

    /**
     * 获取四位数字编号
     *
     * @return 四位数字编号
     */
    String getErrorCode();

    /**
     * 获取错误描述
     *
     * @return
     */
    String getErrorMessage();

    /**
     * 获取完整的业务编码
     *
     * @return 比如，"A-01-0001"
     */
    default String getBusinessCodeString() {
        return getSourceCode() + "-" + DOMAIN_CODE + "-" + getErrorCode();
    }
}
