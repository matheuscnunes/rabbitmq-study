package matheus.nunes.study.rabbitmqpublisher.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SpringFoxConfiguration {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
//                .apiInfo(apiInfo())
    }

//    fun apiInfo(): ApiInfo? {
//        return ApiInfoBuilder()
//                .title("RabbitMQ Publisher API")
//                .description("")
//                .license("")
//                .licenseUrl("http://unlicense.org")
//                .termsOfServiceUrl("")
//                .version("1.0.0")
//                .build()
//    }
}