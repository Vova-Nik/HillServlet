package com.nikolenko.homeworks.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryDataServiceTest {

    @Test
    void parsePathInfo(){
        String country = CountryDataService.parsePathInfo("/");
        assertTrue(country.length()>1000);
        System.out.println(country.substring(0,128) + "... }]");
        country = CountryDataService.parsePathInfo("");
        System.out.println(country);
        assertTrue(country.contains("3 letter"));
        country = CountryDataService.parsePathInfo("hvjksxh");
        assertTrue(country.contains("3 letter"));
        country = CountryDataService.parsePathInfo("/UKR");
        System.out.println(country);
        assertTrue(country.contains("Ukraine"));
        country = CountryDataService.parsePathInfo("/ukr");
        System.out.println(country);
        assertTrue(country.contains("Ukraine"));
        country = CountryDataService.parsePathInfo("/nnn");
        System.out.println(country);
        assertTrue(country.contains("There is no country with code nnn"));
    }

    @Test
    void getJsonById() {
        String country = CountryDataService.getJsonById("BGD");
        System.out.println(country);
        assertTrue(country.contains("\"code\" : \"BGD\""));
        country = CountryDataService.getJsonById("bgd");
        System.out.println(country);
        assertTrue(country.contains("\"code\" : \"BGD\""));
    }

    @Test
    void getAllJson() {
        String countries = CountryDataService.getAllJson();
        System.out.println(countries.substring(0,128) + "... }]");
        assertTrue(countries.contains("{\"code\":"));
        assertTrue(countries.length()>1000);
    }

}