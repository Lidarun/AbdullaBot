package com.lidarunium.web.controller;

import com.lidarunium.web.dto.Quote;
import com.lidarunium.web.enums.Lang;
import com.lidarunium.web.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final QuoteService quoteService;

    @GetMapping
    public Quote getQuote() {
        return quoteService.getQuote(Lang.RU);
    }
}
