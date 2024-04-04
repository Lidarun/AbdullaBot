package com.lidarunium.web.service;

import com.lidarunium.web.dto.Quote;
import com.lidarunium.web.enums.Lang;

public interface QuoteService {
    Quote getQuote(Lang lang);
}
