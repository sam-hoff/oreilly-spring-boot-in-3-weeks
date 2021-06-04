package com.oreilly.restclient.services

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JokeServiceTest {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var service: JokeService

    @Test
    fun getJoke() {
        val joke = service.getJoke("Craig", "Walls")
        logger.info { joke }
        assertTrue(
            joke?.contains("Craig") == true ||
                    joke?.contains("Walls") == true
        )
    }
}

