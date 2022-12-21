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
            val testcontainers = MapPropertySource(
                "testcontainers", createConnectionConfiguration()
            )
            environment.propertySources.addFirst(testcontainers)
        }

        companion object {

            private var postegres: PostgreSQLContainer<*> = PostgreSQLContainer("postgresql:14.5")

            private fun startContainers() {
                Startables.deepStart(Stream.of(postegres)).join()
            }

            private fun createConnectionConfiguration(): MutableMap<String, Any> {
                return java.util.Map.of(
                    "spring.datasource.url", postegres.jdbcUrl,
                    "spring.datasource.username", postegres.username,
                    "spring.datasource.password", postegres.password,
                )
            }
        }
    }
}