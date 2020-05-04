package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
import models.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        try {
            //moved "SELECT * FROM ads" from executeQuery in line 36 to a sql variable
            //then called sql in a PreparedStatement to ensure better security.
            //PreparedStatement is like the ticket communicating the order from server to cook.
            String sql = "SELECT * FROM ads";
            PreparedStatement stmt = connection.prepareStatement(sql);//order ticket = is it visible/prepare this order/order
            ResultSet rs = stmt.executeQuery();//Completed dish = dish.makeIt
            return createAdsFromResults(rs);
        } catch (SQLException e) {//why ur dish didnt make it to ur table
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            //insertQueryString made to insert into a prepared statement
            String insertQueryString = "INSERT INTO ads(user_id, title, description) VALUES (?,?,?)";
            //Return Generated Keys
            PreparedStatement stmt = connection.prepareStatement(insertQueryString, Statement.RETURN_GENERATED_KEYS);
            //this puts in the values to the question marks above
            //helps verify the types for those inputs so that no one can mess with your code through those fields.
                stmt.setLong(1,ad.getUserId());
                stmt.setString(2,ad.getTitle());
                stmt.setString(3,ad.getDescription());
                stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();//going line by line and returns true if there's code that matches the query, false if nothing.

            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
