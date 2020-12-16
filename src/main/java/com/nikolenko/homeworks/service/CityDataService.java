package com.nikolenko.homeworks.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolenko.homeworks.dao.City;
import com.nikolenko.homeworks.dao.CityRepository;
import com.nikolenko.homeworks.dao.Country;
import com.nikolenko.homeworks.dao.CountryRepository;

import java.util.ArrayList;
import java.util.List;

public class CityDataService {

    public static String getJsonById(Long id) {
        CityRepository cityRepository = new CityRepository();
        City city = cityRepository.getById((id));
        String res = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            res = objectMapper.writeValueAsString(city);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getAllJson() {
        CityRepository cityRepository = new CityRepository();
        List<City> cities = cityRepository.getAll();
        String res = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            res = objectMapper.writeValueAsString(cities);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

}




