package com.husu.common.config;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Jackson 自定义
 *
 * @author Ricardo.Y.Hu
 * @date 2023/6/25 10:46
 */
@Configuration
public class JacksonConfig {

    /**
     * 所有的字符串类型，全部Trim
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .deserializerByType(String.class, new StdScalarDeserializer<String>(String.class) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
                            throws IOException {
                        // Trim All String Field
                        return StringUtils.trimWhitespace(jsonParser.getValueAsString());
                    }
                });
    }
}

