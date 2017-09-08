package com.github.weewar.mapviewer.utils;

import com.github.weewar.mapviewer.exceptions.ClassPathResourceException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ClassPath {
    private final static ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public static List<URL> resources(String locationPattern) throws ClassPathResourceException {
        try {
            List<URL> resourceURLs = new LinkedList<>();
            for (Resource resource : resourceResolver.getResources(locationPattern)) {
                resourceURLs.add(resource.getURL());
            }
            return resourceURLs;
        } catch (IOException e) {
            throw new ClassPathResourceException("Unable to locate resources in a classpath: " + locationPattern, e);
        }
    }

    public static URL resource(String path) throws ClassPathResourceException {
        try {
            return resourceResolver.getResource(path).getURL();
        } catch (IOException e) {
            throw new ClassPathResourceException("Unable to locate resource in a classpath: " + path, e);
        }
    }
}
