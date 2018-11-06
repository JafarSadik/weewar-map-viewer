package com.github.weewar.mapviewer.startup

import com.github.weewar.mapviewer.dao.memory.InMemoryWeewarMapDAO
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PopulateInMemoryDAO {
    private val logger = getLogger(PopulateInMemoryDAO::class.java)

    @Autowired(required = false)
    fun onStartup(inMemoryDao: InMemoryWeewarMapDAO) {
        logger.info("Populating in-memory repository")
        inMemoryDao.populate()
    }
}