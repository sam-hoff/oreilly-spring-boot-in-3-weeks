package com.example.geocoder.services

import com.example.geocoder.entities.Site
import com.example.geocoder.json.Response
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.stream.Collectors




@Service
class GeocoderService(private val builder: WebClient.Builder) {
    private val KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno"
    val client: WebClient = builder.baseUrl("https://maps.googleapis.com").build()

    fun getLatLng(vararg address: String): Site? {
        val encoded: String =
            address.joinToString(",") { component -> URLEncoder.encode(component, StandardCharsets.UTF_8) }
        val path = "/maps/api/geocode/json"
        val response = client.get()
            .uri { uriBuilder: UriBuilder ->
                uriBuilder.path(path)
                    .queryParam("address", encoded)
                    .queryParam("key", KEY)
                    .build()
            }
            .retrieve()
            .bodyToMono(Response::class.java)
            .block(Duration.ofSeconds(2))

        return if (response != null) {
            Site(response.getFormattedAddress(), response.getLocation()?.lat, response.getLocation()?.lng)
        } else {
            null
        }
    }
}