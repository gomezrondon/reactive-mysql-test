package com.gomezrondon.reactivemysqltest;


import com.gomezrondon.reactivemysqltest.entities.Order;
import com.gomezrondon.reactivemysqltest.repositories.OrderRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner run(OrderRepository repository){
		//Deletes All -> Inserts 1 -> Find All then prints
		repository.deleteAll()
				.subscribe(null, null,
						() -> repository.save(new Order("Javier"))
								.subscribe(null,null,
										() -> repository.findAll().subscribe(System.out::println)));
		return null;
	}
}

