package com.nikolenko.homeworks.homework_25;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository<Country> {

    @Override
    public Country getById(String id) {
        String query = "SELECT * FROM Country WHERE Code = '" + id + "'";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        ConnectionFactory.nextResultSet(resultSet);
        return parseRecord(resultSet);
    }

    @Override
    public List<Country> getAll() {
        String query = "SELECT * FROM Country WHERE 1";
        List<Country> result = new ArrayList<Country>();
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        while (ConnectionFactory.nextResultSet(resultSet)) {
            result.add(parseRecord(resultSet));
        }
        return result;
    }

    @Override
    public Long count() {
        String query = "SELECT COUNT(*) FROM Country";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        long result = 0L;
        try {
            resultSet.next();
            result = resultSet.getLong("COUNT(*)");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public Country insert(Country country) {
        String sql = "INSERT INTO Country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) " +
                "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepSt = ConnectionFactory.getPreparedStatement(sql);
        int rows =0;
        if(prepSt != null){
            try {
                prepSt.setString(1, country.getCode());
                prepSt.setString(2, country.getName());
                prepSt.setString(3, country.getContinent());
                prepSt.setString(4, country.getRegion());
                prepSt.setDouble(5, country.getSurfaceArea());
                prepSt.setInt(6, country.getIndepYear());
                prepSt.setInt(7, country.getPopulation());
                prepSt.setDouble(8, country.getLifeExpectancy());
                prepSt.setDouble(9, country.getGnp());
                prepSt.setDouble(10, country.getGnpOld());
                prepSt.setString(11, country.getLocalName());
                prepSt.setString(12, country.getGovernmentForm());
                prepSt.setString(13, country.getHeadOfState());
                prepSt.setInt(14, country.getCapital());
                prepSt.setString(15, country.getCode2());
                rows = prepSt.executeUpdate();
            } catch (Exception e) {
                System.out.println("CountryRepository insert error " + e.toString());
                e.printStackTrace();
            }
        }
        ConnectionFactory.preparedStatementClose(prepSt);
        if(rows==0){
            return null;
        }
        return getById(country.getCode());
    }

    @Override
    public void delete(String id) {
        if (id.equals("1") || id.length() != 3 || id.contains("*") || id.contains("?")) {
            return;
        }
        if(!exists(id)){
            return;
        }
        String query = "DELETE FROM Country WHERE Code ='" + id + "'";
        Statement stmt = ConnectionFactory.getStatement();
        ConnectionFactory.updateQuery(query);
    }

    @Override
    public boolean exists(String id) {
        String query = "SELECT COUNT(1) FROM Country WHERE Code = " + "'" + id + "'";
        ResultSet resultSet = ConnectionFactory.getResultSet(query);
        long result = 0;
        try {
            resultSet.next();
            result = resultSet.getLong("COUNT(1)");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result > 0;
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

    @Override
    public void close(){
        ConnectionFactory close;
    }

    private Country parseRecord(ResultSet resultSet) {
        Country country = null;
        try {
            country = Country.builder()
                    .code(resultSet.getString("Code"))
                    .name(resultSet.getString("Name"))
                    .continent(resultSet.getString("Continent"))
                    .region(resultSet.getString("Region"))
                    .surfaceArea(resultSet.getDouble("SurfaceArea"))
                    .indepYear(resultSet.getInt("IndepYear"))
                    .population(resultSet.getInt("Population"))
                    .lifeExpectancy(resultSet.getDouble("LifeExpectancy"))
                    .gnp(resultSet.getDouble("GNP"))
                    .gnpOld(resultSet.getDouble("GNPOld"))
                    .localName(resultSet.getString("LocalName"))
                    .governmentForm(resultSet.getString("GovernmentForm"))
                    .headOfState(resultSet.getString("HeadOfState"))
                    .capital(resultSet.getInt("Capital"))
                    .code2(resultSet.getString("Code2"))
                    .build();
        } catch (SQLException e) {
            System.out.println("Bad SQL " + e.toString());
            return country;
        }
        return country;
    }

}
