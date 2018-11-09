package com.github.weewar.mapviewer.utils

import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.IOException
import java.net.URL
import java.util.*

object ClassPath {
    private val resourceResolver = PathMatchingResourcePatternResolver()

    @Throws(ClassPathResourceException::class)
    fun resources(locationPattern: String): List<URL> {
        try {
            val resourceURLs = LinkedList<URL>()
            for (resource in resourceResolver.getResources(locationPattern)) {
                resourceURLs.add(resource.url)
            }
            return resourceURLs
        } catch (e: IOException) {
            throw ClassPathResourceException("Unable to locate resources in a classpath: $locationPattern", e)
        }

    }

    @Throws(ClassPathResourceException::class)
    fun resource(path: String): URL {
        try {
            return resourceResolver.getResource(path).url
        } catch (e: IOException) {
            throw ClassPathResourceException("Unable to locate resource in a classpath: $path", e)
        }

    }
}

class ClassPathResourceException(message: String, cause: Throwable) : RuntimeException(message, cause)