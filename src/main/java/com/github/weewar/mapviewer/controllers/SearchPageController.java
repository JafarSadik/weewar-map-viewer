package com.github.weewar.mapviewer.controllers;


import com.github.weewar.mapviewer.dao.MapSearchCriteria;
import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import com.github.weewar.mapviewer.model.MapHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchPageController {
    private final WeewarMapDAO weewarMapDAO;

    @Autowired
    public SearchPageController(WeewarMapDAO weewarMapDAO) {
        this.weewarMapDAO = weewarMapDAO;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search-page";
    }

    @ResponseBody
    @GetMapping(value = {"/api/maps"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MapHeader> getMaps(@RequestParam(value = "first", required = false, defaultValue = "0") Integer first,
                                   @RequestParam(value = "count", required = false, defaultValue = "50") Integer count) {
        MapSearchCriteria searchCriteria = new MapSearchCriteria().setFirstElement(first).setPageSize(count);
        return weewarMapDAO.findMapHeaders(searchCriteria);
    }
}
