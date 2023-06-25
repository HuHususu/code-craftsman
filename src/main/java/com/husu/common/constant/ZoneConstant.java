package com.husu.common.constant;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时区ID
 * <br/>
 *
 * @author: ricardo
 */
@UtilityClass
public class ZoneConstant {
    /**
     * UTC
     */
    public static final String UTC = "UTC";
    /**
     * 太平洋时间，也是美国洛杉矶的时区
     */
    public static final String LOS_ANGELES = "America/Los_Angeles";
    /**
     * 中国标准时间，北京时间
     */
    public static final String ASIA_SHANGHAI = "Asia/Shanghai";
    /**
     * 一般模式
     */
    public static final String GENERAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 特殊模式1
     */
    public static final String SPECIAL_PATTERN_1 = "MM/dd/yyyy HH:mm:ss";
    /**
     * 特殊模式2
     */
    public static final String SPECIAL_PATTERN_2 = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 特殊模式-天（MM/dd/yyyy）
     */
    public static final String SPECIAL_PATTERN_DAY = "MM/dd/yyyy";

    /**
     * 特殊模式-小时（MM/dd/yyyy HH）
     */
    public static final String SPECIAL_PATTERN_HOUR = "MM/dd/yyyy HH";

    /**
     * 特殊模式-分钟（MM/dd/yyyy HH:mm）
     */
    public static final String SPECIAL_PATTERN_MINUTE = "MM/dd/yyyy HH:mm";

    /**
     * ISO 8601格式，AmazonFeed要求格式
     */
    public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    /**
     * ISO 8601格式
     */
    public static final String ISO_8601_SPECIAL_2 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    /**
     * ISO 8601格式，eBay要求格式，用于UTC时间带Z尾缀
     */
    public static final String ISO_8601_SPECIAL_3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    /**
     * 微软日期格式，C#时间类型参数大量使用此格式
     */
    public static final String MICROSOFT_DATE_FORMAT = "/Date(%s)/";
    /**
     * 美国时区
     */
    public static final ZoneId AMERICAN_ZONE = ZoneId.of(ZoneConstant.LOS_ANGELES);

    /**
     * 特殊时间格式化对象: MM/dd/yyyy
     */
    public static final DateTimeFormatter SPECIAL_PATTERN_DAY_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.SPECIAL_PATTERN_DAY);

    /**
     * 特殊时间格式化对象: MM/dd/yyyy HH
     */
    public static final DateTimeFormatter SPECIAL_PATTERN_HOUR_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.SPECIAL_PATTERN_HOUR);

    /**
     * 特殊时间格式化对象: MM/dd/yyyy HH:mm
     */
    public static final DateTimeFormatter SPECIAL_PATTERN_MINUTE_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.SPECIAL_PATTERN_MINUTE);

    /**
     * 常规时间格式化对象:yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter GENERAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.GENERAL_PATTERN);
    /**
     * 特殊模式2:yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final DateTimeFormatter SPECIAL_PATTERN_2_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.SPECIAL_PATTERN_2);
    /**
     * ISO_8601时间格式化对象: yyyy-MM-dd'T'HH:mm:ssXXX
     */
    public static final DateTimeFormatter ISO8601_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.ISO_8601);
    /**
     * ISO_8601时间格式化对象: yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter ISO8601_SPECIAL_2_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.ISO_8601_SPECIAL_2);
    /**
     * ISO_8601时间格式化对象: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'，只针对带Z尾缀的UTC时间
     */
    public static final DateTimeFormatter ISO8601_SPECIAL_3_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ZoneConstant.ISO_8601_SPECIAL_3);
}
