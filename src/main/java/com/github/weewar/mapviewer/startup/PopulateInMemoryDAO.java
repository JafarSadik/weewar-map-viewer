package com.github.weewar.mapviewer.startup;

import com.github.weewar.mapviewer.dao.memory.InMemoryWeewarMapDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateInMemoryDAO {
    private final Logger logger = LoggerFactory.getLogger(InMemoryWeewarMapDAO.class);

    @Autowired(required = false)
    public void onStartup(InMemoryWeewarMapDAO inMemoryMapDAO) {
        logger.info("Populating in-memory DAO.");
        inMemoryMapDAO.populate();
    }
}