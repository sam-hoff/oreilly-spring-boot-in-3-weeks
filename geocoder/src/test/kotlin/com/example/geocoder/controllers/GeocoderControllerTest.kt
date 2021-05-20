package com.example.geocoder.controllers

import com.example.geocoder.entities.Site
import com.example.geocoder.services.GeocoderService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(GeocoderController::class)
class GeocoderControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockBean
    private lateinit var geocoderService: GeocoderService

    @Test
    fun `get request returns location`() {
        `when`(geocoderService.getLatLng("Boston", "MA"))
            .thenReturn(Site("Boston, MA, USA", 42.36, -71.06)
        )
        mockMvc.perform(get("/geolocation?address=Boston,MA").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.lat").value(42.36))
    }
}