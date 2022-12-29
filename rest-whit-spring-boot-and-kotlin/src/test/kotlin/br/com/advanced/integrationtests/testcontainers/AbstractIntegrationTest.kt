package br.com.advanced.integrationtests.testcontainers

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

            private var postgre: PostgreSQLContainer<*> = PostgreSQLContainer("PostgreSQL 14.5")

            private fun startContainers() {
                Startables.deepStart(Stream.of(postgre)).join()
            }

            private fun createConnectionConfiguration(): MutableMap<String, Any> {
                return java.util.Map.of(
                    "spring.datasource.url", postgre.jdbcUrl,
                    "spring.datasource.username", postgre.username,
                    "spring.datasource.password", postgre.password,
                )
            }
        }
    }
}