package com.example.jdbcexample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*


@Repository
class JdbcCustomerDao(@Autowired val jdbcTemplate: JdbcTemplate) : CustomerDao {
    val insertCustomer = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("customers")
        .usingGeneratedKeyColumns("id")


    var rowMapper: RowMapper<Customer> = RowMapper<Customer> { rs: ResultSet, rowNum: Int ->
        Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("id"))
    }

    override fun save(customer: Customer): Customer {
        val parameters = mapOf("id" to customer.id, "first_name" to customer.firstName, "last_name" to customer.lastName)
        val newId = insertCustomer.executeAndReturnKey(parameters) as Int
        customer.id = newId
        return customer
    }

    override fun findById(id: Int?): Customer? {
        if (!existsById(id)) {
            return null
        }
        return jdbcTemplate.queryForObject(
            "SELECT * FROM customers WHERE id=?",
            rowMapper
        ,id
        )
    }

    override fun findAll(): List<Customer?>? {
        return jdbcTemplate.query(
            "SELECT * FROM customers",
            rowMapper
        )
    }

    override fun count(): Long {
        return jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM customers", Long::class.java
        )!!
    }

    override fun delete(customer: Customer?) {
        jdbcTemplate.update("DELETE FROM customers WHERE id=?", customer?.id)
    }

    override fun existsById(id: Int?): Boolean {
        return jdbcTemplate.queryForObject(
            "SELECT EXISTS(SELECT 1 FROM customers WHERE id=?)", Boolean::class.java, id
        )!!
    }
}