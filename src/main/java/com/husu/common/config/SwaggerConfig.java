package com.husu.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ricardo
 * <p>
 * Open API 3.0 Reference
 * <p>
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(ObjectMapper objectMapper) {
        ModelConverters.getInstance().addConverter(new ModelResolver(objectMapper));

        // local server
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("LOCAL");

        // dev server
        Server devServer = new Server();
        devServer.setUrl("https://dev");
        devServer.setDescription("DEV");

        return new OpenAPI()
                .addServersItem(localServer)
                .addServersItem(devServer)
                .info(new Info()
                        .title("husu code craftsman")
                        .version("1.0")
                        .description("husu code craftsman"));
    }

    @Bean
    public OperationCustomizer swaggerHeaders() {
        return (operation, handlerMethod) -> {
            Parameter name = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name("X-husu-test")
                    .example("husu")
                    .description("husu")
                    .required(Boolean.TRUE);
            operation.addParametersItem(name);
            return operation;
        };
    }
}
