package com.example.geocoder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeocoderApplication

fun main(args: Array<String>) {
    runApplication<GeocoderApplication>(*args)
}
