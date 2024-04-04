package com.lidarunium.web.service.impl;

import com.lidarunium.web.dto.Quote;
import com.lidarunium.web.enums.Lang;
import com.lidarunium.web.service.QuoteService;
import com.lidarunium.web.service.RuQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final RuQuoteService quoteService;
    @Override
    public Quote getQuote(Lang lang) {
        return Lang.EN.equals(lang) ? quoteService.getRandomEnQuote() : quoteService.getRandomRuQuote();
    }
}
