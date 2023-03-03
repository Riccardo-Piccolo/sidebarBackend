package com.piccolo.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Airline2Application {

	public static void main(String[] args) {
		SpringApplication.run(Airline2Application.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/aereo").allowedOrigins("http://localhost:8080", "http://localhost:4200", "http://localhost:4200");
				registry.addMapping("/aereo/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/getModello").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/postiInterval").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/getPosti").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/annoInterval").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/getAnnoCostruzione").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aereo/getCompagnia").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aeroporto").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aeroporto/getNome").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aeroporto/getCitta").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aeroporto/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/aeroporto/delete").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getCitta").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getData").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getIndirizzo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getNome").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/cliente/getNumero").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/compagnia").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/compagnia/getNome").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/compagnia/getSede").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/compagnia/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/prenotazione").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/prenotazione/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/prenotazione/getCodiceCliente").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/prenotazione/getCodiceVolo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/prenotazione/getData").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/scalo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/scalo/getCodice").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/scalo/getCodiceVolo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/scalo/getDataScalo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/scalo/getOraScalo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getCodiceAereo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getDataArrivo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getDataPartenza").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getOraArrivo").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getOraPartenza").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getPrezzoInterval").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getTerminalDestinazione").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getTerminalOrigine").allowedOrigins("http://localhost:8080", "http://localhost:4200");
				registry.addMapping("/volo/getVoliFromToday").allowedOrigins("http://localhost:8080", "http://localhost:4200");
			}
		};
	}
	
	@Configuration
    public class WebMvcConfiguration implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").
            allowedOrigins("*").
            allowedMethods("*").
            allowedHeaders("*");
        }
    }

}
