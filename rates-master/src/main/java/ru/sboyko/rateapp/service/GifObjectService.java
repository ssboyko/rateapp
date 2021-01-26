package ru.sboyko.rateapp.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sboyko.rateapp.dto.GifDTO;
import ru.sboyko.rateapp.dto.GifObjectApiResponse;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GifObjectService {

    private final static String API_GIF_APP_KEY = "lg480QRVRZr3BVhnMbgmaZCob1MXp3QI";


    private final RestTemplate restTemplate;

    public String getSomeGIF (String searchKey) throws URISyntaxException {
        val uri = new URI(String.format("https://api.giphy.com/v1/gifs/trending?api_key=%s",API_GIF_APP_KEY));

        val giphyResponse = restTemplate.getForObject(uri, GifDTO.class);

        assert giphyResponse != null;
        return giphyResponse.getData()[0].getBitlyurl();
    }

}
