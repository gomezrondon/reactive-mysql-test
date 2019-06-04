package com.gomezrondon.reactivemysqltest.repositories;

import com.gomezrondon.reactivemysqltest.entities.Product;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    @Query("select id, name, price from product where id = $1")
    Mono<Product> findById(Long id);
}