package com.example.controller;

import com.example.service.SearchService;
import com.example.vo.ZillowResult;
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

    @GetMapping("/searchByKeyWord")
    public ZillowResult searchByKeyWord(String city, String content, int page, @RequestParam(defaultValue = "5") int rows) {
        return searchService.searchByKeyWord(city, content, page, rows);
    }

    @GetMapping("/ESReload")
    public ZillowResult searchByKeyWord() {
        searchService.ESInit();
        return ZillowResult.ok("Done");
    }

    @Scheduled(cron = "0 0 */5 * * ?")
    public void scheduledESReload() {
        searchService.ESInit();
        System.out.println("ES Reload scheduled task executed.");
    }

}
