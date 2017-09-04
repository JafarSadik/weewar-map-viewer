package com.github.weewar.mapviewer.startup;

import com.github.weewar.mapviewer.repository.impl.InMemoryMapDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PopulateInMemoryDAO {
    private final Logger logger = LoggerFactory.getLogger(InMemoryMapDAO.class);

    @Autowired(required = false)
    public void onStartup(InMemoryMapDAO inMemoryMapDAO) {
        try {
            logger.info("Populating in-memory DAO.");
            inMemoryMapDAO.populate();
        } catch (IOException exc) {
            logger.error("Failed to populate in-memory DAO!", exc);
            System.exit(1);
        }
    }
}
