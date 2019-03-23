package com.gomezrondon.reactivemysqltest;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;
import com.gomezrondon.reactivemysqltest.entities.Order;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>> OK ");
	}

	@Bean
	ApplicationRunner run(OrderRepository repository){
		return args -> repository.findAll().subscribe(System.out::println);
	}
}


interface OrderRepository extends ReactiveCrudRepository<Order, Long>{}

@Configuration
@EnableR2dbcRepositories
class MysqlApplicationConfig extends AbstractR2dbcConfiguration {
	@Override
	public ConnectionFactory connectionFactory() {
		String url = "mysql://root:root@192.168.99.100:3306/test";
		return new JasyncConnectionFactory(new MySQLConnectionFactory(
				URLParser.INSTANCE.parseOrDie(url, StandardCharsets.UTF_8)
		));
	}
}
