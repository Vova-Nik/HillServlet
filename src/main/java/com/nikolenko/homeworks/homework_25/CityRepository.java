package com.nikolenko.homeworks.homework_25;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository implements Repository<City> {

    @Override
    public City getById(Long id) throws IllegalArgumentException {
        String query = "SELECT * FROM City WHERE ID = " + id;
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        if (resultSet == null) {
            return null;
        }
        ConnectionFactory.nextResultSet(resultSet);
        return parseRecord(resultSet);
    }

    @Override
    public List<City> getAll() {
        String query = "SELECT * FROM City WHERE 1";
        List<City> cities = new ArrayList<>();
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        if (resultSet == null) {
            return null;
        }
        while (ConnectionFactory.nextResultSet(resultSet)) {
            cities.add(parseRecord(resultSet));
        }
        return cities;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM City WHERE ID = " + id;
        if (!exists(id)) {
            return;
        }
        Statement stmt = ConnectionFactory.getStatement();
        ConnectionFactory.updateQuery(query);
    }

    @Override
    public boolean exists(Long id) {
        String query = "SELECT 1 FROM City WHERE ID = " + id + " LIMIT 1";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        long result = 0;
        try {
            if (resultSet != null) {
                resultSet.next();
                result = resultSet.getLong("1");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result > 0;
    }

    @Override
    public Long count() {
        String query = "SELECT COUNT(*) FROM City";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        long result = 0L;
        try {
            if (resultSet != null) {
                resultSet.next();
                result = resultSet.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public City insert(City city) {

        String sql = "INSERT INTO City (Name, CountryCode, District, Population) " +
                "Values (?, ?, ?, ?)";
        PreparedStatement prepSt = ConnectionFactory.getPreparedStatement(sql);
        int rows = 0;
        if (prepSt != null) {
            try {
                prepSt.setString(1, city.getName());
                prepSt.setString(2, city.getCountryCode());
                prepSt.setString(3, city.getDistrict());
                prepSt.setInt(4, city.getPopulation());
                rows = prepSt.executeUpdate();
            } catch (Exception e) {
                System.out.println("CityRepository insert() error " + e.toString());
                e.printStackTrace();
            }
        }
        if (rows == 0) {
            return null;
        }
        ConnectionFactory.preparedStatementClose(prepSt);
        return getByName(city.getName());
    }

    public City getByName(String cityName) throws IllegalArgumentException {
        String query = "SELECT * FROM City WHERE Name = '" + cityName + "'";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        if (resultSet == null) {
            return null;
        }
        ConnectionFactory.nextResultSet(resultSet);
        return parseRecord(resultSet);
    }


    @Override
    public City getById(String id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    @Override
    public void delete(String id) {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    @Override
    public boolean exists(String id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    @Override
    public void close() {
        ConnectionFactory.close();
    }

    private City parseRecord(ResultSet resultSet) {
        City city = null;
        try {
            city = City.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("Name"))
                    .countryCode(resultSet.getString("CountryCode"))
                    .district(resultSet.getString("District"))
                    .population(resultSet.getInt("Population"))
                    .build();
        } catch (SQLException e) {
            System.out.println("Bad SQL. CityRepository.parseRecord()" + e.toString());
            return city;
        }
        return city;
    }
}
