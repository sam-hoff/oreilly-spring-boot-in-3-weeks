package com.example.profileexample.user.rest

import com.example.profileexample.user.data.User
import com.example.profileexample.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(@Autowired private val userService: UserService) {
    @Value("\${helloMessage}")
    private lateinit var helloMessage: String

    @GetMapping
    fun getUsers(): String {
        return helloMessage
    }

    @PostMapping
    fun createUser(): ResponseEntity<User> {
        val newUser = userService.createUser()
        return ResponseEntity
            .created(URI.create("/users/${newUser.id}"))
            .body(newUser)
    }
}