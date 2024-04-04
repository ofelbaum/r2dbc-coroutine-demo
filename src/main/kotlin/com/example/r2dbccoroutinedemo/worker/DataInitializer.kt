package com.example.r2dbccoroutinedemo.worker

import com.github.javafaker.Faker
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.await
import org.springframework.stereotype.Component

@Component
@DependsOn("ConnectionFactoryInitializer")
class DataInitializer : InitializingBean {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    private val logger = LoggerFactory.getLogger(DataInitializer::class.java)

    override fun afterPropertiesSet() {
        runBlocking {
            val count: Long = databaseClient
                .sql("SELECT COUNT(*) FROM addresses")
                .map { row, _ -> row.get(0) }
                .first().map { it.toString().toLong() }.block()!!
            if (count < 500) {
                insertFakeData()
            }
        }
    }

    private suspend fun insertFakeData() {
        logger.info("Adding addresses")
        val faker: Faker = Faker()
        for (i in 0..99) {
            val fakeAddress = faker.address()
            databaseClient.sql(
                """INSERT INTO addresses (country, city, street) VALUES (:country,:city,:street)""".trimIndent()
            )
                .bind("country", fakeAddress.country())
                .bind("city", fakeAddress.city())
                .bind("street", fakeAddress.streetAddress())
                .await()
        }
    }
}