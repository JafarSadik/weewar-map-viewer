package com.github.weewar.mapviewer.utils

import com.github.weewar.mapviewer.model.ClassPathResourceException
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URL
import java.util.*

@Component
open class ClassPath {
    private val resourceResolver = PathMatchingResourcePatternResolver()

    @Throws(ClassPathResourceException::class)
    open fun resources(locationPattern: String): List<URL> =
            try {
                val resourceURLs = LinkedList<URL>()
                for (resource in resourceResolver.getResources("$locationPattern*")) {
                    resourceURLs.add(resource.url)
                }
                resourceURLs
            } catch (e: IOException) {
                throw ClassPathResourceException("Unable to locate resources in a classpath: $locationPattern", e)
            }

    @Throws(ClassPathResourceException::class)
    open fun resource(path: String): URL =
            try {
                resourceResolver.getResource(path).url
            } catch (e: IOException) {
                throw ClassPathResourceException("Unable to locate resource in a classpath: $path", e)
            }
}
