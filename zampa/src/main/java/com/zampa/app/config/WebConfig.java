/* PAQUETE */
package com.zampa.app.config;

/* LIBRERIAS */
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registro) {
		Path directorioSubidas = Paths.get("uploads").toAbsolutePath();
		String rutaSubidas = directorioSubidas.toUri().toString() + "/";
		
		registro.addResourceHandler("/uploads/**").addResourceLocations(rutaSubidas);
	}
}
