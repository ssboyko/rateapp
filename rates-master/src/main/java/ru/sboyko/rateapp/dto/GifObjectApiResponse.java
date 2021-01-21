package ru.sboyko.rateapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class GifObjectApiResponse {
    private String type;
    private String id;
    private String slug;
    private String url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String rating;
    private String content_url;
    private String user;
    private String source_tld;
    private String source_post_url;
    private String update_datetime;
    private String create_datetime;
    private String import_datetime;
    private String trending_datetime;
    private ArrayList<Image> images;
    private String title;

}
