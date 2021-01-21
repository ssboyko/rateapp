package ru.sboyko.rateapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sboyko.rateapp.service.GifObjectService;
import ru.sboyko.rateapp.service.RateService;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;


@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;
    private final GifObjectService gifObjectService;

    //Проба пера получить рандомную гифку, 500ая ошибка, хз почему, дебагом пользовался, как понять где ошибка не пойму
    @GetMapping("/giphy")
    public ArrayList<Image> getSomeGifs () throws URISyntaxException {
        val images = gifObjectService.getSomeGIF();
        return images;
    }


    @GetMapping("/{currency}")
    public String getUsdRateByCurrency(@PathVariable("currency") String currency) {
        val currencyRate = rateService.getRubleRateToCurrency(currency);
        return String.format("USD rate to %s is %s", currency.toUpperCase(), currencyRate.toString());
    }

    @GetMapping("/yesterday/{currency}")
    public String getYesterdayRubleRateToCurrency(@PathVariable("currency") String currency) {
        val YesterdayCurrencyRate = rateService.getRubleRateToCurrency(currency);
        return String.format(" USD rate to %s was %s", currency.toUpperCase(), YesterdayCurrencyRate.toString());
    }


    //Результирующий метод, идея такова:
    //Если курс по отношению к рублю за сегодня стал выше вчерашнего, отдаём одну строку, и наоборот, если курса стал ниже, отдаём другую строку
    // Впоследствии поменяю строку на объект ГИФ
    @GetMapping("/{currency}/result")
    public String wasYesterdayRubCourseHigherThanNow (@PathVariable("currency") String currency){
        //Для проверки в чём дело и почему основной метод не работает, я решил проверить, отдаёт ли этот метод просто строку - результат из
        //метода сегодняшнего и вчерашнего курса

        //Метод полчения сегодняшнего курса работает
        //Double todayRate = rateService.getRubleRateToCurrency(currency);
        //return String.format("today rate is %s", todayRate.toString());

        //А метод получения строки вчерашнего курса выбивает 500ую ошибку!
       //Double yesterdayRate = rateService.getYesterdayRubleRateToCurrency(currency);
       //return String.format("yesterday rate was %s", yesterdayRate.toString());




        //Это основной метод. Он не работает валится на 63 строке, которая в свою очередь ругается на 33 строку класса RateService. Хз что ей надо.
//        Double todayRate = rateService.getRubleRateToCurrency(currency);
//        Double yesterdayRate = rateService.getYesterdayRubleRateToCurrency(currency);
//        if (todayRate > yesterdayRate){
//            return String.format("USD rate to %s today = %s.Yesterday was = %s . Today rate is higher than yesterday",currency.toUpperCase(),
//                    todayRate.toString(), yesterdayRate.toString());
//        }
//        else
//            return String.format("USD rate to %s today = %s.Yesterday was = %s . Yesterday rate was higher than today",currency.toUpperCase(),
//                    todayRate.toString(), yesterdayRate.toString());
    }
}