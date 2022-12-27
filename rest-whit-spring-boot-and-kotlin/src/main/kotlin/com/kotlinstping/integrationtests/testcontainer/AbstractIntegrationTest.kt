package com.kotlinstping.integrationtests.testcontainer

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.lifecycle.Startables
import java.util.stream.Stream
@ContextConfiguration(initializers = [AbstractIntegrationTest.Initializer::class])
open class AbstractIntegrationTest {

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext>{

        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            startContainers()

            val environment = applicationContext.environment
            val testContainers = MapPropertySource(
                "testContainers", createConnectionConfiguration()
            )
            environment.propertySources.addFirst(testContainers)
        }

        companion object {

            private var postegresql: PostgreSQLContainer<*> = PostgreSQLContainer("postgresql:14.5")

            private fun startContainers() {
                Startables.deepStart(Stream.of(postegresql)).join()
            }

            private fun createConnectionConfiguration(): MutableMap<String, Any> {
                return java.util.Map.of(
                    "spring.datasource.url", postegresql.jdbcUrl,
                    "spring.datasource.username", postegresql.username,
                    "spring.datasource.password", postegresql.password,
                )
            }
        }
    }
}