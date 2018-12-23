package com.yulece.app.management.comments.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringBootSwagger2Bootstrap {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yulece.app.management.comments.provider"))
                .paths(PathSelectors.regex("/async/.*"))
                .build();
    }


    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("APP-异步服务接口")
                .description("APP-异步服务接口")
                .termsOfServiceUrl("127.0.0.1:8010")
                .contact("1026290752qq.com")
                .build();
    }
}
