package com.example.r2dbccoroutinedemo.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
class Configuration {

    @Bean("ConnectionFactoryInitializer")
    fun initializer(connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory!!)
        initializer.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))

        return initializer
    }
}