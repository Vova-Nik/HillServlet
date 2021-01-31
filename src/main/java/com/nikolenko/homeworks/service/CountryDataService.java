package com.nikolenko.homeworks.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolenko.homeworks.dao.Country;
import com.nikolenko.homeworks.dao.CountryRepository;

import java.util.List;

public class CountryDataService {
    public static String parsePathInfo(String requestPathInfo) {
        if (requestPathInfo == null) {
            return getAllJson();
        }
        requestPathInfo = requestPathInfo.toLowerCase();
        if(requestPathInfo.equals("/")){
            return getAllJson();
        }
        if (requestPathInfo.length() != 4) {
            return "Can not process request. Country code should be 3 letter length.";
        }
        requestPathInfo = requestPathInfo.substring(1, 4);

        String res = getJsonById(requestPathInfo);
        if(res.equals("null")){
            res = "There is no country with code " + requestPathInfo;
        }
        return res;
    }

    public static String getJsonById(String code) {
        CountryRepository countryRepository = new CountryRepository();
        Country country = countryRepository.getById(code);
        String res = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(country);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getAllJson() {
        CountryRepository countryRepository = new CountryRepository();
        List<Country> countries = countryRepository.getAll();
        String res = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            res = objectMapper.writeValueAsString(countries);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }
}




