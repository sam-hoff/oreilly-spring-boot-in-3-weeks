package com.oreilly.restclient.services

import com.oreilly.restclient.entities.Site
import com.oreilly.restclient.json.GeocoderResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.net.URLEncoder
import org.springframework.web.reactive.function.client.bodyToMono

import org.springframework.web.util.UriBuilder
import java.nio.charset.StandardCharsets
import java.time.Duration

const val GOOGLE_API_KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

@Service
class GeocoderService(
    @Autowired val builder: WebClient.Builder
) {
    val client = builder
        .baseUrl("https://maps.googleapis.com")
        .build()

    fun getLatLng(vararg address: String?): Site? {
        val encoded = address.joinToString(separator = ",") {
            URLEncoder.encode(
                it,
                StandardCharsets.UTF_8
            )
        }
        val path = "/maps/api/geocode/json"
        val response = client.get()
            .uri { uriBuilder: UriBuilder ->
                uriBuilder.path(path)
                    .queryParam("address", encoded)
                    .queryParam("key", GOOGLE_API_KEY)
                    .build()
            }
            .retrieve()
            .bodyToMono<GeocoderResponse>()
            .block(Duration.ofSeconds(2))
        return if (response != null) {
            val (lat, lng) = response.getLocation()
            Site(
                response.getFormattedAddress(),
                lat,
                lng
            )
        } else {
            null
        }
    }
}