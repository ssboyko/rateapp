package ru.sboyko.rateapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.ArrayList;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifObjectApiResponse {
    private String url;
    @JsonProperty(value = "bitly_url")
    private String bitlyurl;
    private String title;

}
