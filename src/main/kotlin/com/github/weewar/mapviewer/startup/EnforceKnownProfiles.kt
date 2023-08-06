package com.github.weewar.mapviewer.startup

import org.slf4j.LoggerFactory.getLogger
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import kotlin.system.exitProcess


@Component
class EnforceKnownProfiles(env: Environment) {
    private val logger = getLogger(EnforceKnownProfiles::class.java)
    private val profiles: List<String> = listOf(*env.activeProfiles)

    @PostConstruct
    fun onStartup() {
        val prod = profiles.contains("prod")
        val dev = profiles.contains("dev")

        expect(prod || dev, "One of the following spring boot profiles is required: 'dev' or 'prod'. " +
                "Please, activate proper profile like this: java -jar -Dspring.profiles.active=prod weewar-map-viewer.jar")

        expect(prod xor dev, "Cannot use 'dev' and 'prod' profiles at the same time.")
    }

    private fun expect(condition: Boolean, errorMessage: String) {
        if (!condition) {
            logger.error(errorMessage)
            exitProcess(1)
        }
    }
}
