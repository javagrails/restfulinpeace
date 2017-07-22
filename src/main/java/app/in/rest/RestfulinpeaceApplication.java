package app.in.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName ServletInitializer.java | app.in.rest | ServletInitializer
 * @since Jul 19, 2017 14:34:56 PM
 */


@SpringBootApplication
@EnableSwagger2
public class RestfulinpeaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulinpeaceApplication.class, args);
    }


    @Bean
    public Docket lastApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Restfulinpeace")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Restful in Peace Application")
                .description(" A simple REST service for Salman software ")
                .contact(new Contact("M. M. Saleh (Salman)", "https://github.com/javagrails", "Java Grails"))
                .version("1.0")
                .build();
    }
}
