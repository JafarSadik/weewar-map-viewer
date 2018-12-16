package com.github.weewar.mapviewer.startup

import com.github.weewar.mapviewer.dao.memory.InMemoryWeewarMapDAO
import org.slf4j.LoggerFactory.getLogger
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PopulateInMemoryDAO(val inMemoryDao: InMemoryWeewarMapDAO) {
    private val logger = getLogger(PopulateInMemoryDAO::class.java)

    @EventListener(ApplicationReadyEvent::class)
    fun onStartup() {
        logger.info("Populating in-memory repository")
        inMemoryDao.populate()
    }
}