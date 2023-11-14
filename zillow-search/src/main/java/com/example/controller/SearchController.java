package com.example.controller;

import com.example.service.SearchService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    //    @GetMapping("/getSearchResults")
    @GetMapping("/search")
    public ZillowResult search(String city, String content, int page, @RequestParam(defaultValue = "5") int rows) {
        return searchService.search(city, content, page, rows);
    }

    @GetMapping("/ESReload")
    public ZillowResult search() {
        searchService.ESInit();
        return ZillowResult.ok("Done");
    }

}
