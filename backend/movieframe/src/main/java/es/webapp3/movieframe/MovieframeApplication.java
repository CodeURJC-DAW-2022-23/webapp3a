package es.webapp3.movieframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MovieframeApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(MovieframeApplication.class, args);
	}

}
