package za.co.addressing.customeraddressing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{

	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings( CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
				.maxAge(MAX_AGE_SECS);
	}
}
