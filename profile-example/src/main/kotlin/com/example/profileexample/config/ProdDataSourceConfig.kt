package com.example.profileexample.config

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("prod")
class ProdDataSourceConfig : DataSourceConfig {
    override fun setUp() {
        println("setting up data source for prod")
    }
}