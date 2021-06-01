package com.example.jdbcexample

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class JdbcCustomerDaoTest(@Autowired private var dao: CustomerDao){

    @Test
    fun save() {
        val customer = Customer("sam", "hoffman", null)
        val savedCustomer = dao.save(customer)
        assertNotNull(savedCustomer.id)
    }

    @Test
    fun findById() {
        val customer = dao.findById(1)
        assertNotNull(customer)
        assertEquals(1, customer?.id)
    }

    @Test
    fun findAll() {
        val allCustomers = dao.findAll()
        assertNotNull(allCustomers)
        assertEquals(allCustomers?.size, 4)
    }

    @Test
    fun count() {
        assertEquals(dao.count(), 4)
    }

    @Test
    fun delete() {
        (1..4).forEach { id ->
            val customer = dao.findById(id)
            assertNotNull(customer)
            dao.delete(customer)
        }
        assertEquals(dao.count(), 0)
    }

    @Test
    fun existsById() {
        val customer = dao.findById(2)
        assertNotNull(customer)
    }

    @Test
    fun doesNotExistById() {
        val customer = dao.findById(999)
        assertNull(customer)
    }
}