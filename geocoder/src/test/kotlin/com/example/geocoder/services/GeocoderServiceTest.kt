package com.example.geocoder.services

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GeocoderServiceTest(@Autowired private val geocoderService: GeocoderService) {

    private val logger: Logger = LoggerFactory.getLogger(GeocoderServiceTest::class.java)

    @Test
    fun `get lat lng without street`() {
        val site = geocoderService.getLatLng("Boston", "MA")
        assertAll(
            { assertEquals(42.36, site?.lat!!, 0.01) },
            { assertEquals(-71.06, site?.lng!!, 0.01) }
        )
    }

    @Test
    fun `get lat lng with street`() {
        val site = geocoderService.getLatLng("1600 Ampitheatre Parkway", "Mountain View", "CA")
        assertAll(
            { assertEquals(37.42, site?.lat!!, 0.01) },
            { assertEquals(-122.08, site?.lng!!, 0.01) }
        )
    }
}