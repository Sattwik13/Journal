package com.journal.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journal.journalApp.apiResponse.weatherResponse;

@Component
public class weatherService {

    private static final String apiKey = "a9329f739245a9c605f97b7694ffb404";

    // private static final String API = "https://api.weatherstack.com/current?access_key=a9329f739245a9c605f97b7694ffb404&query=Mumbai";
    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY"; 

    @Autowired
    private RestTemplate restTemplete;


    public weatherResponse getWeather(String city){
       String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
       ResponseEntity<weatherResponse> response = restTemplete.exchange(finalAPI, HttpMethod.GET, null, weatherResponse.class);
       weatherResponse body = response.getBody();
       return body;
    }

}

