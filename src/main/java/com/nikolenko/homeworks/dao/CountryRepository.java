package com.nikolenko.homeworks.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository<Country> {

    @Override
    public Country getById(String id) {
        //        String query = "SELECT * FROM Country WHERE Code = ?"; //Need do add Capital as String instead of long key
        String query = "SELECT * FROM(SELECT S.*, G.Name as Cap FROM Country as S, City as G WHERE S.Capital = G.Id) as cl WHERE Code LIKE ?";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return parseRecord(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Country> getAll() {
        String query = "SELECT S.*, G.Name as Cap FROM Country as S, City as G WHERE S.Capital = G.Id";
        List<Country> countries = new ArrayList<>();
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                countries.add(parseRecord(rs));
            }
            return countries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long count() {
        String query = "SELECT COUNT(*) FROM Country";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return rs.getLong("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Country insert(Country country) {
        String query = "INSERT INTO Country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) " +
                "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, country.getCode());
            prepStmt.setString(2, country.getName());
            prepStmt.setString(3, country.getContinent());
            prepStmt.setString(4, country.getRegion());
            prepStmt.setDouble(5, country.getSurfaceArea());
            prepStmt.setInt(6, country.getIndepYear());
            prepStmt.setInt(7, country.getPopulation());
            prepStmt.setDouble(8, country.getLifeExpectancy());
            prepStmt.setDouble(9, country.getGnp());
            prepStmt.setDouble(10, country.getGnpOld());
            prepStmt.setString(11, country.getLocalName());
            prepStmt.setString(12, country.getGovernmentForm());
            prepStmt.setString(13, country.getHeadOfState());
            prepStmt.setInt(14, country.getCapital());
            prepStmt.setString(15, country.getCode2());
            prepStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getById(country.getCode());
    }

    @Override
    public void delete(String id) {
        if (id.equals("1") || id.length() != 3 || id.contains("*") || id.contains("?")) {
            return;
        }
        if (!exists(id)) {
            return;
        }
        String query = "DELETE FROM Country WHERE Code = ?";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, id);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String id) {
        String query = "SELECT COUNT(1) FROM Country WHERE Code = ?";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        long result;
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            result = rs.getLong("1");
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Country getById(Long Id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class Country");
    }

    @Override
    public void delete(Long Id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class Country");
    }

    @Override
    public boolean exists(Long Id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class Country");
    }

    private Country parseRecord(ResultSet resultSet) {
        Country country = null;
        try {
//            String sss = resultSet.getString("Name");
            country = new Country(resultSet.getString("Code"),
                    resultSet.getString("Name"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Region"),
                    resultSet.getDouble("SurfaceArea"),
                    resultSet.getInt("IndepYear"),
                    resultSet.getInt("Population"),
                    resultSet.getDouble("LifeExpectancy"),
                    resultSet.getDouble("GNP"),
                    resultSet.getDouble("GNPOld"),
                    resultSet.getString("LocalName"),
                    resultSet.getString("GovernmentForm"),
                    resultSet.getString("HeadOfState"),
                    resultSet.getInt("Capital"),
                    resultSet.getString("Cap"),
                    resultSet.getString("Code2"));

        } catch (SQLException e) {
            System.out.println("Bad SQL " + e.toString());
            return country;
        }
        return country;
    }

//    @Override
//    public String getJsonById(Long id){
//        Country country = this.getById(id);
//        String res = "";
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            res = objectMapper.writeValueAsString(country);
//        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

//    @Override
//    public  String getAllJson() {
//        List<Country> countries = this.getAll();
//        String res = "";
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            res = objectMapper.writeValueAsString(countries);
//        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}
