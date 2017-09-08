package com.github.weewar.mapviewer.utils;

import com.github.weewar.mapviewer.exceptions.ClassPathResourceException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClassPath {
    private final static ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public static List<Resource> resources(String locationPattern) throws ClassPathResourceException {
        try {
            return Arrays.asList(resourceResolver.getResources(locationPattern));
        } catch (IOException e) {
            throw new ClassPathResourceException("Unable to locate resource in a classpath: " + locationPattern, e);
        }
    }
}
