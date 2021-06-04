package com.oreilly.restclient.services

import com.oreilly.restclient.json.JokeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.Duration

@Service
class JokeService(
    @Autowired val builder: WebClient.Builder
) {
    private val client = builder
        .baseUrl("http://api.icndb.com")
        .build()

    fun getJoke(first: String, last: String): String? {
        val path = "/jokes/random"
        return client.get().uri { uriBuilder ->
            uriBuilder.path(path)
                .queryParam("limitTo", "[nerdy]")
                .queryParam("firstName", first)
                .queryParam("lastName", last)
                .build()
        }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono<JokeResponse>()
            .map { jokeResponse -> jokeResponse.value.joke }
            .block(Duration.ofSeconds(2))

    }
}