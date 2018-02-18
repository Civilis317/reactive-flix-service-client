package net.playground.flixserviceclient;

import net.playground.flixserviceclient.model.Movie;
import net.playground.flixserviceclient.model.MovieEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FlixServiceClientApplication {
	Logger logger = LoggerFactory.getLogger(FlixServiceClientApplication.class.getName());

	@Bean
	WebClient client () {
		return WebClient.builder().filter(ExchangeFilterFunctions
				.basicAuthentication("admin","admin"))
				.baseUrl("http://localhost:8080/movies")
				.build();
	}

	@Bean
	CommandLineRunner demo (WebClient client) {
		return args -> client.get()
				.retrieve()
				.bodyToFlux(Movie.class)
				.filter(movie -> movie.getTitle().equalsIgnoreCase("Silence of the Lambdas"))
				.flatMap(movie -> client.get().uri("/{id}/events", movie.getId()).retrieve().bodyToFlux(MovieEvent.class))
				.subscribe(e -> logger.info(e.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(FlixServiceClientApplication.class, args);
	}
}
