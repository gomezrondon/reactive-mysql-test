package com.gomezrondon.reactivemysqltest.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;
import com.gomezrondon.reactivemysqltest.handler.ProductHandler;
import com.gomezrondon.reactivemysqltest.repositories.OrderRepository;
import com.gomezrondon.reactivemysqltest.repositories.ProductRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@EnableR2dbcRepositories(basePackageClasses={OrderRepository.class, ProductRepository.class})
public class Config extends AbstractR2dbcConfiguration {
    @Override
    public ConnectionFactory connectionFactory() {
        String url = "mysql://root:root@localhost:3306/test";
        return new JasyncConnectionFactory(new MySQLConnectionFactory(
                URLParser.INSTANCE.parseOrDie(url, StandardCharsets.UTF_8)
        ));
    }




}