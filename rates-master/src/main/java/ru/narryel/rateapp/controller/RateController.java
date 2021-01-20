package ru.narryel.rateapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.narryel.rateapp.service.RateService;

import java.time.LocalDate;


@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;
    private final LocalDate today = LocalDate.now();
    private final LocalDate yesterday = today.minusDays(1);

    @GetMapping("/{currency}")
    public String getUsdRateByCurrency(@PathVariable("currency") String currency) {
        val currencyRate = rateService.getRubleRateToCurrency(currency);
        //Double currencyRate = rateService.getRubleRateToCurrency(currency);
        return String.format("USD rate to %s is %s", currency.toUpperCase(), currencyRate.toString());
    }

    @GetMapping("/yesterday/{currency}")
    public String getYesterdayRubleRateToCurrency(@PathVariable("currency") String currency) {
        val currencyRate = rateService.getRubleRateToCurrency(currency);
        //Double currencyRate = rateService.getYesterdayRubleRateToCurrency(currency);
        return String.format(" USD rate to %s was %s", currency.toUpperCase(), currencyRate.toString());
    }
}