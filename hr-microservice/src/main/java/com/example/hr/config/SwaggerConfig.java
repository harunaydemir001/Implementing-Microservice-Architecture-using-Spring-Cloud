package com.example.hr.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	@Value("${apiMajorVersion}")
	private String apiMajorVersion;
	@Value("${apiMinorVersion}")
	private String apiMinorVersion;
	@Value("${apiTimeStamp}")
	private long apiTimeStamp;
	@Value("${server.servlet.context-path}")
	private String contextPath;
	@Value("${spring.mvc.servlet.path}")
	private String servletPath;

	@Value("${server.address}")
	private String host;

	@Value("${server.port}")
	private long port;

	@Bean
	public Docket api(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.hr")).paths(PathSelectors.any()).build()
				.host(host.concat(":").concat(Long.toString(port)))
				.pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return contextPath;
                    }
                })
		      .securityContexts(Arrays.asList(securityContext()))
		      .securitySchemes(Arrays.asList(apiKey()))
			  .apiInfo(apiInfo());
	}

	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    var authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {authorizationScope})); 
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("HR MicroServices")
				.description("<b>Client FrontEnd API</b><br /><br />Updated: [" + (new Date(apiTimeStamp)).toString()
						+ " ]" + " <script>document.title = \"CRM MicroServices\";"
						+ " document.getElementById('header').remove();" + "</script>")
				.version(apiMajorVersion + "." + apiMinorVersion).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
