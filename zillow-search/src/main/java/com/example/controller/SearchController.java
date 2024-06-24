package com.example.controller;

import com.example.service.SearchService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/searchByCity")
    public BaseResult searchByCity(String city, int page, @RequestParam(defaultValue = "5") int rows) {
        return searchService.searchByCity(city, page, rows);
    }

    @GetMapping("/searchByKeyWord")
    public BaseResult searchByKeyWord(String city, String content, int page, @RequestParam(defaultValue = "5") int rows) {
        return searchService.searchByKeyWord(city, content, page, rows);
    }

    @GetMapping("/ESReload")
    public BaseResult esReload() {
        searchService.esinit();
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Done");
        return ok;
    }

    @Scheduled(cron = "0 0 */5 * * ?")
    public void scheduledESReload() {
        searchService.esinit();
    }

}
