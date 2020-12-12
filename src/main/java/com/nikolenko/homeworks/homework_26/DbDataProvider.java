package com.nikolenko.homeworks.homework_26;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikolenko.homeworks.homework_25.City;
import com.nikolenko.homeworks.homework_25.CityRepository;
import com.nikolenko.homeworks.homework_25.Country;
import com.nikolenko.homeworks.homework_25.CountryRepository;

import java.util.ArrayList;
import java.util.List;

public class DbDataProvider {

    public static List<City> getCities() {
        CityRepository cityRepository = new CityRepository();
        return cityRepository.getAll();
    }

    public static StringBuilder getCitiesHTML() {
        CityRepository cityRepository = new CityRepository();
        List<City> cities = cityRepository.getAll();
        StringBuilder sb = new StringBuilder();
        for (City city : cities) {
            sb.append("<div class='city-container'>")
                    .append("<div class='city-item city-id'>")
                    .append(city.getId())
                    .append("</div>")
                    .append("<div class='city-item city-name'>")
                    .append(city.getName())
                    .append("</div>")
                    .append("<div class='city-item city-countryCode'>")
                    .append(city.getCountryCode())
                    .append("</div>")
                    .append("<div class='city-item city-district'>")
                    .append(city.getDistrict())
                    .append("</div>")
                    .append("<div class='city-item city-population'>")
                    .append(city.getPopulation())
                    .append("</div>")
                    .append("</div>");
        }
        return sb;
    }

    public static StringBuilder getCountriesCSV() {
        CountryRepository countryRepository = new CountryRepository();
        List<Country> countries = countryRepository.getAll();
        StringBuilder sb = new StringBuilder();
        sb
                .append("Code").append(',')
                .append("Name").append(',')
                .append("continent").append(',')
                .append("Region").append(',')
                .append("Surface Area").append(',')
                .append("Indep nYear").append(',')
                .append("Population").append(',')
                .append("LifeExpectancy").append(',')
                .append("GNP").append(',')
                .append("GNPOld").append(',')
                .append("LocalName").append(',')
                .append("GovernmentForm").append(',')
                .append("HeadOfState").append((char) 13);

        for (Country country : countries) {
            sb
                    .append(country.getCode()).append(',')
                    .append(country.getName()).append(',')
                    .append(country.getContinent()).append(',')
                    .append(country.getRegion()).append(',')
                    .append(country.getSurfaceArea()).append(',')
                    .append(country.getIndepYear()).append(',')
                    .append(country.getPopulation()).append(',')
                    .append(country.getLifeExpectancy()).append(',')
                    .append(country.getGnp()).append(',')
                    .append(country.getGnpOld()).append(',')
                    .append(country.getLocalName()).append(',')
                    .append(country.getGovernmentForm()).append(',')
                    .append(country.getHeadOfState()).append(',')
                    .append(country.getCapital()).append(',')
                    .append(country.getCode2()).append((char) 13);
        }
        return sb;
    }

    public static String deleteCity(int id) {
        CityRepository cityRepository = new CityRepository();
        if (!cityRepository.exists((long) id)) {
            return "There is no city with id = " + (long) id;
        }
        City cityToDelete = cityRepository.getById((long) id);
        cityRepository.delete((long) id);
        cityRepository.close();
        return cityToDelete.getName();
    }

    public static StringBuilder getCitiesGSON() {
        CityRepository cityRepository = new CityRepository();
        List<City> cities = cityRepository.getAll();

        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();

        Gson gson = new GsonBuilder().create();
        return new StringBuilder(gson.toJson(cities));
    }

    public static StringBuilder getCountriesJGSON(){
        CountryRepository countryRepository = new CountryRepository();
        List<Country> countries = countryRepository.getAll();
        Gson gson = new GsonBuilder().create();
        return new StringBuilder(gson.toJson(countries));
    }

    public static StringBuilder getStatJGSON(){
        List<String> stat = new ArrayList<>();
        CountryRepository countryRepository = new CountryRepository();
        stat.add(String.valueOf(countryRepository.count()));
        CityRepository cityRepository = new CityRepository();
        String str = String.valueOf(cityRepository.count());
        stat.add(str);

        Gson gson = new GsonBuilder().create();
        return new StringBuilder(gson.toJson(stat));
    }


}




