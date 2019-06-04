package com.gomezrondon.reactivemysqltest.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("product")
data class Product(@Id val id: Long=0, val name: String = "", val price: Double = 0.0) {

}