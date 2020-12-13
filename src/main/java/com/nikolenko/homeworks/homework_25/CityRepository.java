package com.nikolenko.homeworks.homework_25;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository implements Repository<City> {

    @Override
    public City getById(Long id) {
        String query = "SELECT * FROM City WHERE ID = ?";
        PreparedStatement prepStmt;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return parseRecord(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM City WHERE 1";
        PreparedStatement prepStmt = null;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                cities.add(parseRecord(rs));
            }
            return cities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long count() {
        String query = "SELECT COUNT(*) FROM City";
        PreparedStatement prepStmt = null;
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

    public City insert(City city) {
        String query = "INSERT INTO City (Name, CountryCode, District, Population) " +
                "Values (?, ?, ?, ?)";
        PreparedStatement prepStmt = null;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, city.getName());
            prepStmt.setString(2, city.getCountryCode());
            prepStmt.setString(3, city.getDistrict());
            prepStmt.setInt(4, city.getPopulation());
            prepStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getByName(city.getName());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM City WHERE ID = ?";
        if (!exists(id)) {
            return;
        }
        PreparedStatement prepStmt = null;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, id);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(Long id) {
        String query = "SELECT 1 FROM City WHERE ID = ?  LIMIT 1";
        PreparedStatement prepStmt = null;
        DataSource ds = PooledDataSource.getDataSource();
        long result = 0;
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            result = rs.getLong("1");
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public City getByName(String cityName) {
        String query = "SELECT * FROM City WHERE Name =  ? ";

        PreparedStatement prepStmt = null;
        DataSource ds = PooledDataSource.getDataSource();
        try (Connection connection = ds.getConnection()) {
            prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, cityName);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return parseRecord(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public City getById(String id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    @Override
    public void delete(String id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    @Override
    public boolean exists(String id) throws IllegalArgumentException {
        throw new IllegalArgumentException("There is no String type key in class City");
    }

    private City parseRecord(ResultSet resultSet) {
        City city = null;
        try {
            city = new City(resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population"));
        } catch (SQLException e) {
            System.out.println("Bad SQL. CityRepository.parseRecord()" + e.toString());
            return city;
        }
        return city;
    }
}
