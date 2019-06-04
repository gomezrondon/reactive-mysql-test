package com.gomezrondon.reactivemysqltest;


import com.gomezrondon.reactivemysqltest.entities.Product;
import com.gomezrondon.reactivemysqltest.repositories.OrderRepository;
import com.gomezrondon.reactivemysqltest.repositories.ProductRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner run(OrderRepository repository, ProductRepository prodRepo){
		return args -> {
			Flux<Product> productFlux = Flux.just(
					new Product(0, "Cafe Grande", 2.99),
					new Product(0, "Cafe pequeno", 0.99),
					new Product(0, "Cafe tetero", 2.0)
			).flatMap(prodRepo::save);


			prodRepo.deleteAll()
					.thenMany(productFlux)
					.thenMany(prodRepo.findAll())
					.subscribe(System.out::println);


			/*
			//This works
			 prodRepo.findAllById(Flux.<Long>just(17L,18L)).subscribe(System.out::println);
			 prodRepo.findAllById(Mono.<Long>just(17L)).subscribe(System.out::println);
			 prodRepo.findById(17L).subscribe(System.out::println);
			 */
		};
	}
}

