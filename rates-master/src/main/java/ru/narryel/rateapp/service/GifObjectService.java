package ru.narryel.rateapp.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.narryel.rateapp.dto.ExchangeRatesApiResponse;
import ru.narryel.rateapp.dto.GifObjectApiResponse;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GifObjectService {

    private final static String API_GIF_APP_KEY = "lg480QRVRZr3BVhnMbgmaZCob1MXp3QI";

    private final RestTemplate restTemplate;

    public ArrayList<Image> getSomeGIF () throws URISyntaxException {
        val uri = new URI(String.format("api.giphy.com/v1/gifs/trending%app_id=%s", API_GIF_APP_KEY));
        val response = restTemplate.getForObject(uri, GifObjectApiResponse.class);

        assert response != null;
        return response.getImages();
    }

}
