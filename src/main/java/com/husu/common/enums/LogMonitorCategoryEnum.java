package com.husu.common.enums;

import lombok.Getter;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/8 0:30
 */
@Getter
public enum LogMonitorCategoryEnum {
    /**
     * 发送MQ监控
     */
    MQ_SEND(CategoryConstants.MQ, "send"),
    QUERY_IN_STOCK_API(CategoryConstants.LOCATION_INVENTORY, "instock-list"),
    ;


    /**
     * 日志主分类
     */
    private static class CategoryConstants {
        public static final String MQ = "MQ";
        public static final String LOCATION_INVENTORY = "LocationInventory";
    }

    /**
     * 主类型
     */
    private final String category;
    /**
     * 子类型
     */
    private final String subCategory;

    LogMonitorCategoryEnum(String category, String subCategory) {
        this.category = category;
        this.subCategory = subCategory;
    }
}
