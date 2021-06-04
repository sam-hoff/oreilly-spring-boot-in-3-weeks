package com.example.profileexample

import com.example.profileexample.user.data.User
import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import javax.sql.DataSource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
internal class UserIntegrationTest(@Autowired private val restTemplate: TestRestTemplate) {
    @Autowired private lateinit var datasource: DataSource

    @Test
    fun `datasource drive is h2`() {
        assertEquals("org.h2.Driver", (datasource as HikariDataSource).driverClassName)
    }

    @Test
    fun `create new user returns success`() {
        val headers = HttpHeaders()
        headers.set("Location", "/users/12345")
        val request: HttpEntity<User> = HttpEntity(User(12345), headers)
        val entity = restTemplate.postForEntity<String>("/users", request)
        assertEquals(HttpStatus.CREATED, entity.statusCode)
    }


}