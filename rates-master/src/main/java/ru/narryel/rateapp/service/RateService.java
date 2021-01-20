package ru.narryel.rateapp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.narryel.rateapp.dto.ExchangeRatesApiResponse;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RateService {

    public static final String RATES_API_APP_KEY = "35b85788d95c4debbd776948d82e3240";

    public static final String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private final RestTemplate restTemplate;



    @SneakyThrows
    public Double getYesterdayRubleRateToCurrency(String currency) {
        //val uri = new URI(String.format("https://openexchangerates.org/api/historical/%s/json?app_id=%s",LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE)), RATES_API_APP_KEY);
        val uri = "https://openexchangerates.org/api/historical/2021-01-19.json?app_id=35b85788d95c4debbd776948d82e3240";
        val response = restTemplate.getForObject(new URI(uri), ExchangeRatesApiResponse.class);
        val currencyKey = currency.toUpperCase();
        assert response != null;
        val currencyRate = response.getRates().get(currencyKey);
        if (currencyRate == null) {
            throw new RuntimeException(String.format("Не пришел курс по валюте с ключом %s", currency ));
        }
        return currencyRate;
    }

    @SneakyThrows
    public Double getRubleRateToCurrency(String currency) {
        val uri = new URI(String.format("https://openexchangerates.org/api/latest.json?app_id=%s", RATES_API_APP_KEY));
        val response = restTemplate.getForObject(uri, ExchangeRatesApiResponse.class);
        val currencyKey = currency.toUpperCase();
        assert response != null;
        val currencyRate = response.getRates().get(currencyKey);
        if (currencyRate == null) {
            throw new RuntimeException(String.format("Не пришел курс по валюте с ключом %s", currency ));
        }
        return currencyRate;
    }



}
