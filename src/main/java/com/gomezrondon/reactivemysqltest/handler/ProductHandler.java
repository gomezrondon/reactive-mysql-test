package com.gomezrondon.reactivemysqltest.handler;


import com.gomezrondon.reactivemysqltest.entities.Product;
import com.gomezrondon.reactivemysqltest.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ProductHandler {

    private final ProductRepository repository;

    public ProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        Flux<Product> products = repository.findAll();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products, Product.class);

    }


    public Mono<ServerResponse> getProduct(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Product> productMono = this.repository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return productMono
                .flatMap(product ->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(product)))
                .switchIfEmpty(notFound);

    }


    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        Mono<Product> productMono = request.bodyToMono(Product.class);

        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(repository.save(product), Product.class));
    }


    public Mono<ServerResponse> updateProduct(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Product> exitingProductMono = this.repository.findById(id);
        Mono<Product> productMono = request.bodyToMono(Product.class);

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return productMono.zipWith(exitingProductMono,
                (product, existingProduct) -> new Product(existingProduct.getId(), product.getName(), product.getPrice()))
                .flatMap(product -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(repository.save(product), Product.class))
                .switchIfEmpty(notFound);

    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));

        Mono<Product> productMono = this.repository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return productMono
                .flatMap(product ->
                        ServerResponse.ok()
                                .build(repository.delete(product)))
                .switchIfEmpty(notFound);

    }


}
