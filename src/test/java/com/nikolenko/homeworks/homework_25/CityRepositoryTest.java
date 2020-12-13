package com.nikolenko.homeworks.homework_25;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryTest {

//        private static final String host = "jdbc:mysql://mysql316.1gb.ua/";
//        private static final String dataBaseName = "gbua_hillwrld";
//        private static final String init = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//        private static final String username = "gbua_hillwrld";
//        private static final String password = "ba84d96c6xvn";

//    private static final String host = "jdbc:mysql://localhost/world";
//    private static final String dataBaseName = "world";
//    private static final String username = "mysql";
//    private static final String password = "mysql";
//    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private static CityRepository cityRepository;
    private static BasicDataSource dataSource;

    @BeforeAll
    public static void setup() {
        cityRepository = new CityRepository();
    }

    /*
    Dont forget to run DB
     */


    @Test
    void getById() {
        City city = cityRepository.getById(1L);
        System.out.println(city);
        assertTrue(city.toString().contains("City"));
    }

    @Test
    void getAll() {
        List<City> city = cityRepository.getAll();
        System.out.println(city.get(128));
        assertTrue(city.get(129).toString().contains("City"));
    }

    @Test
    void delete() {
        cityRepository.delete(100000L);
    }

    @Test
    void exists() {
        assertTrue(cityRepository.exists(128L));
    }

    @Test
    void count() {
        assertTrue(cityRepository.count()>1000);
    }

    @Test
    void insert() {
    }

    @Test
    void getByName() {
        City city = cityRepository.getByName("Amsterdam");
        if(!city.getName().equals("Amsterdam")) {
            city = cityRepository.getByName("Odessa");
        }
        assertTrue(city.toString().contains("City"));
    }

    @Test
    void testGetById() {
        assertThrows(IllegalArgumentException.class, ()->cityRepository.getById("VVV"));
    }

    @Test
    void testDelete() {
    }

    @Test
    void testExists() {
    }
}