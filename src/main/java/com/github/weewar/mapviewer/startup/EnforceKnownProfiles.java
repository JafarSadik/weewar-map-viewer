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
        checkCondition(prod || dev, "One of the following spring boot profiles is required: 'dev' or 'prod'. " +
                "Please, activate proper profile like this: java -jar -Dspring.profiles.active=prod weewar-map-viewer.jar");
        checkCondition(prod ^ dev, "Cannot use 'dev' and 'prod' profiles at the same time.");
    }

    private void checkCondition(boolean conditionFulfilled, String description) {
        if (!conditionFulfilled) {
            logger.error(description);
            System.exit(1);
        }
    }
}
