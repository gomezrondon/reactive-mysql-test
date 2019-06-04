package com.gomezrondon.reactivemysqltest.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("product")
data class Product(val name:String="", val price:Double=0.0) {
    @Id
    var id:Long = 0
}