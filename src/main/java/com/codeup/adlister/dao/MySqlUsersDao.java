package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import models.Config;

import java.sql.*;

public class MySqlUsersDao implements Users{
    //added Connection
    //What does this do?
    private Connection connection = null;

    //created constructor for mysqlusersdao
    public MySqlUsersDao(Config config) {
        try{
            //what is the difference between driver and driver manager?
            //What is happening with the Driver?
            DriverManager.registerDriver(new Driver());
//            I don't understand why we're doing config.get
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUsername(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        PreparedStatement stmt;
        User user = null;
        //**********inserted try catch**********
        try {
            String sql = "SELECT * FROM users WHERE username = ?;";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            user = extractUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
        return user;
    }
    private User extractUser(ResultSet rs) throws SQLException{
        if (!rs.next()) return null;
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    @Override
    public Long insert(User user) {
        //what does this -1 for id do?
        long id = -1;
        //*********inserted try catch***********
        try {
            //getInsertQuery is here from the method below
            PreparedStatement stmt = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            //what does this do?
            stmt.executeUpdate();
            //Why stmt.getGeneratedKeys when we have Statement.RETURN_GENERATED_KEYS above
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getLong(1);
        } catch (SQLException e) {
            System.err.printf(e.getMessage());
        }
        return id;
    }

    //*******created method getInsertQuery*******
    private String getInsertQuery() {
        return "INSERT INTO users(username, email, password) VALUES (?, ?, ?);";
    }
}
