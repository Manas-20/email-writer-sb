package com.email.writer.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@OpenAPIDefinition(
	info = @Info(title = "Email Writer API", version = "1.0", description = "Generates emails"),
	servers = {
		@Server(url = "https://email-writer-sb-production-6521.up.railway.app", description = "Production Server")
	}
)
@SpringBootApplication
public class EmailWriterSbApplication {

	public static void main(String[] args) {
		// Print environment variables for debugging (excluding sensitive ones)
		System.out.println("Starting application with environment:");
		System.getenv().forEach((key, value) -> {
			if (!key.toLowerCase().contains("key") && !key.toLowerCase().contains("secret") && !key.toLowerCase().contains("password")) {
				System.out.println(key + " = " + value);
			} else {
				System.out.println(key + " = [REDACTED]");
			}
		});

		SpringApplication.run(EmailWriterSbApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;
	}
}
