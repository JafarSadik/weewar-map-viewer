package com.github.weewar.mapviewer.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class EnforceKnownProfiles {
    private final Logger logger = LoggerFactory.getLogger(EnforceKnownProfiles.class);
    private final List<String> profiles;

    @Autowired
    public EnforceKnownProfiles(Environment env) {
        this.profiles = asList(env.getActiveProfiles());
    }

    @PostConstruct
    public void onStartup() {
        boolean prod = profiles.contains("prod"), dev = profiles.contains("dev");
        enforceCondition(prod || dev, "One of the following spring boot profiles is required: 'dev' or 'prod'");
        enforceCondition(prod ^ dev, "Cannot use 'dev' and 'prod' profiles at the same time");
    }

    private void enforceCondition(boolean condition, String description) {
        if (!condition) {
            logger.error(description);
            System.exit(1);
        }
    }
}
