package com.brq.digital.workshop.bookrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title ("API de Livros")
                .description ("Essa é a API de Livros criada para o treinamento.")
                .license("License of API")
                .licenseUrl("API license URL")
                .termsOfServiceUrl("Terms of service")
                .version("1.0.0")
                .contact(new Contact("Marcel Ferry","www.marcelferry.com.br", "marcelferry@marcelferry.com.br"))
                .build();
        		
        return apiInfo;
    }
}