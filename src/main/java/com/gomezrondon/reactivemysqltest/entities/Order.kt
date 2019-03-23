package com.gomezrondon.reactivemysqltest.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("orders")
data class Order(val name:String="") {
    @Id var id:Long = 0
}