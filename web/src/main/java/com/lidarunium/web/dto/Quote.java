package com.lidarunium.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quote {
    @JsonProperty("quoteText")
    private String quote;
    @JsonProperty("quoteAuthor")
    private String author;
}
