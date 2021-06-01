package com.example.jdbcexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JdbcExampleApplication

fun main(args: Array<String>) {
    runApplication<JdbcExampleApplication>(*args)
}
