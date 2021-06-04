package com.oreilly.restclient.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GeocoderServiceTest(
    @Autowired val service: GeocoderService
) {
    val logger: Logger = LoggerFactory.getLogger(GeocoderServiceTest::class.java)

    @Test
    fun `get lat lng without street`() {
        val site = service.getLatLng("Boston", "MA")
        logger.info(site.toString())
        assertAll(
            "test that lat and long are correct for location without address",
            { assertEquals(42.3600825, site?.lattitude) },
            { assertEquals(-71.0588801, site?.longitude) }
        )
    }

    @Test
    fun `get lat lng with street`(){
        val site = service.getLatLng("1600 Amphitheatre Parkway", "Mountain View", "CA")
        logger.info(site.toString())
        assertAll(
            "test that lat and long are correct for location with address",
            { assertEquals(37.4211581, site?.lattitude) },
            { assertEquals(-122.0837367, site?.longitude) }
        )
    }
}