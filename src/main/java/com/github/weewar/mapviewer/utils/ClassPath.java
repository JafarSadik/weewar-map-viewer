package com.github.weewar.mapviewer.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClassPath {
    private final static ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public static List<Resource> resources(String locationPattern) throws IOException {
        return Arrays.asList(resourceResolver.getResources(locationPattern));
    }
}
