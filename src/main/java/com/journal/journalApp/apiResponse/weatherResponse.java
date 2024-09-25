package com.journal.journalApp.apiResponse;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class weatherResponse{
    private Current current;

  @Getter    
  @Setter  
  public class Current{
    // private String observation_time;

    private int temperature;

    @JsonProperty("weather_code")
    private int weatherCode;

    // private ArrayList<String> weather_icons;
    // private ArrayList<String> weather_descriptions;
    // private int wind_speed;
    // private int wind_degree;
    // private String wind_dir;
    private int pressure;
    private double precip;
    private int humidity;
    private int cloudcover;
    private int feelslike;
    // private int uv_index;
    private int visibility;
    // private String is_day;
  }



}

