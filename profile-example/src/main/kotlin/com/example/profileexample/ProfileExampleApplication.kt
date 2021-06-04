package com.example.profileexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// Spring Profiles provide a way to segregate parts of your application configuration
// and make it only available in certain environments.
@SpringBootApplication
class ProfileExampleApplication

fun main(args: Array<String>) {
    runApplication<ProfileExampleApplication>(*args)
}
