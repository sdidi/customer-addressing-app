package za.co.addressing.customeraddressing.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

	@Bean
	public Docket addressValidationApi(){
		return new Docket( DocumentationType.SWAGGER_2)
				.groupName("addressing")
				.select()
					.apis( RequestHandlerSelectors.any())
					.paths( PathSelectors.ant(  "/addressapi/**" ))
					.build()
				.apiInfo( metadata() );

	}

	@Bean
	public Docket countryValidationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("countries")
				.apiInfo(metadata())
				.select()
				.paths( PathSelectors.ant(  "/countryapi/**" ))
				.build();
	}

	@Bean
	public Docket provinceValidationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("provincies")
				.apiInfo(metadata())
				.select()
				.paths( PathSelectors.ant(  "/provinceapi/**" ))
				.build();
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title( "Sabre Addressing API " )
				.description( "All APIs in this application" )
				.version( "1.0" )
				.build();
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addResourceHandlers( ResourceHandlerRegistry registry )
			{
				registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
				registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
			}
		};
	}
}