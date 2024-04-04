package com.lidarunium.web.service;

import com.lidarunium.web.dto.Quote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ruQuote", url = "https://api.forismatic.com/api/1.0")
public interface RuQuoteService {
    @GetMapping(value = "/?method=getQuote&format=json&lang=en", produces = MediaType.APPLICATION_JSON_VALUE)
    Quote getRandomEnQuote();

    @GetMapping(value = "/?method=getQuote&format=json&lang=ru", produces = MediaType.APPLICATION_JSON_VALUE)
    Quote getRandomRuQuote();
}
