package com.gomezrondon.reactivemysqltest.config;


import com.gomezrondon.reactivemysqltest.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfiguration {

    @Bean
    RouterFunction<?> routes(ProductHandler handler) {
        return nest(path("/products"),
                nest(accept(APPLICATION_JSON).or(contentType(APPLICATION_JSON)),
                        route(GET("/"),handler::getAllProducts)
                            .andRoute(method(HttpMethod.POST),handler::saveProduct)
                            .andRoute(method(HttpMethod.DELETE),handler::deleteAllProduct)
                            .andNest(path("/{id}"),
                                route(method(HttpMethod.GET), handler::getProduct))
                                .andRoute(method(HttpMethod.DELETE),handler::deleteProduct)
                                .andRoute(method(HttpMethod.PUT),handler::updateProduct)
                                )
        );
    }

}
