package com.husu.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/25 10:54
 */
@Slf4j
@UtilityClass
public class UriUtil {
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 模式匹配
     *
     * @param patternUri 模式URI
     * @param uri        URI
     * @return
     */
    public static boolean match(String patternUri, String uri) {
        Objects.requireNonNull(uri, "uri must not be empty");
        Objects.requireNonNull(patternUri, "patternUri must not be empty");
        return ANT_PATH_MATCHER.match(patternUri, uri);
    }

    /**
     * 安全编码
     *
     * @param content
     * @param enc
     * @return
     */
    public static String safeEncode(String content, String enc) {
        Objects.requireNonNull(enc, "enc must not be empty");
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        try {
            return URLEncoder.encode(content, enc);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 安全解码
     *
     * @param content
     * @param enc
     * @return
     */
    public static String safeDecode(String content, String enc) {
        Objects.requireNonNull(enc, "enc must not be empty");
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        try {
            return URLDecoder.decode(content, enc);
        } catch (Exception ex) {
            return "";
        }
    }
}
