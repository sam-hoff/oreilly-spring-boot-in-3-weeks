package com.example.profileexample.user.service

import com.example.profileexample.user.data.User
import com.example.profileexample.user.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class UserService(@Autowired private val userRepository: UserRepository) {
    fun createUser(): User {
        val id = Random.nextLong()
        return userRepository.save(User(id))
    }
}