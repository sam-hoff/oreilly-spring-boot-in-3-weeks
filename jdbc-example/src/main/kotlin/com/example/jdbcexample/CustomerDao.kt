package com.example.jdbcexample

import java.util.*

interface CustomerDao {
    fun save(customer: Customer): Customer
    fun findById(id: Int?): Customer?
    fun findAll(): List<Customer?>?
    fun count(): Long
    fun delete(customer: Customer?)
    fun existsById(id: Int?): Boolean
}