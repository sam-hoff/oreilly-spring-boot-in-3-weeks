package com.example.geocoder.controllers

import com.example.geocoder.entities.Site
import com.example.geocoder.services.GeocoderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/geolocation")
class GeocoderController(@Autowired private val geocoderService: GeocoderService) {
    @GetMapping
    fun getGeolocation(@RequestParam address: List<String>): Site? {
        return geocoderService.getLatLng(*address.toTypedArray())
    }
}