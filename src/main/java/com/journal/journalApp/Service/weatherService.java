package com.journal.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journal.journalApp.apiResponse.weatherResponse;
import com.journal.journalApp.cache.appCache;
import com.journal.journalApp.constants.placeHolder;

@Component
public class weatherService {

    // private static final String apiKey = "a9329f739245a9c605f97b7694ffb404";
    @Value("${weather.api.key}")
    private String apiKey;

    // private static final String API = "https://api.weatherstack.com/current?access_key=a9329f739245a9c605f97b7694ffb404&query=Mumbai";
    // private static final String API = "https://api.weatherstack.com/current?access_key=<API_KEY>&query=<CITY>"; 

    @Autowired
    private RestTemplate restTemplete;

    @Autowired
    private appCache APP_CACHE;


    // public weatherResponse getWeather(String city){
    //    String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
    //    ResponseEntity<weatherResponse> response = restTemplete.exchange(finalAPI, HttpMethod.GET, null, weatherResponse.class);
    //    weatherResponse body = response.getBody();
    //    return body;
    // }


    @Autowired
    private redisService RedisService;

    public weatherResponse getWeather(String city){
        weatherResponse WeatherResponse = RedisService.get("weather_of_" + city , weatherResponse.class);
        if(WeatherResponse != null) {
          return WeatherResponse;
        }else{
            String finalAPI = APP_CACHE.AppCache.get(appCache.keys.WEATHER_API.toString()).replace(placeHolder.CITY, city).replace(placeHolder.API_KEY, apiKey);
            ResponseEntity<weatherResponse> response = restTemplete.exchange(finalAPI, HttpMethod.POST, null, weatherResponse.class);
            weatherResponse body = response.getBody();
            if(body != null) {
                RedisService.set("weather_of_"+ city, body, 300l);
            }
            return body;
        }
        
     }

}

