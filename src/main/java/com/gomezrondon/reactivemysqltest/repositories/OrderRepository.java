package com.gomezrondon.reactivemysqltest.repositories;

import com.gomezrondon.reactivemysqltest.entities.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {}
