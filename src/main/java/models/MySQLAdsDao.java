package models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO: Create a class named MySQLAdsDao that implements the Ads interface
public class MySQLAdsDao implements Ads{

//    TODO: This class should have a private instance property named connection
//     of type Connection that is initialized in the constructor.
    private Connection conn;

//    TODO: Define your constructor so that it accepts
//     an instance of your Config class so that it can obtain
//     the database credentials.
//    **************NEED EXPLANATION*****************
    public MySQLAdsDao() throws SQLException {

        //Instantiating Config object
        Config config = new Config();

        //Setup database Driver
        DriverManager.registerDriver(new Driver());

        // Get a connection object
        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );

    }

//TODO: Implement the methods in the Ads interface

//    todo:Your methods should retrieve ads from the database
    @Override
    public List<Ad> all() {
        List<Ad> allAds = new ArrayList<>();

        //query of ads from DB table
        String query = "SELECT * FROM ads";

        //needs to handle sql exceptions
        try {
            //create a statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //iterate through the rs data and add each ad to our 'Ad' bean
            while (rs.next()){
                allAds.add(
                        new Ad(
                                rs.getLong("id"),
                                rs.getLong("users_id"),
                                rs.getString("title"),
                                rs.getString("description")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //need to return ArrayList with all Ads from DB
        return allAds;
    }

//    todo:and insert new ads into the database
    @Override
    public Long insert(Ad ad) {
        long newAdId = 0;

        //query DB to add an Ad
        String newAddQuery = String.format("INSERT INTO ads (user_id, title, description) VALUES (%d, '%s', '%s');",
                ad.getUserId(),
                ad.getTitle(),
                ad.getDescription());

        //need to handle sql exceptions
        try {
            //create a statement object
            Statement stmt = conn.createStatement();

            //execute statement to add a new Ad
            stmt.executeUpdate(newAddQuery, Statement.RETURN_GENERATED_KEYS);//*****what generated keys******

            //Save newly created statement from line 87 to rs
            ResultSet rs = stmt.getGeneratedKeys();

            //set new Ad id in the DB to newAdId variable
            if(rs.next()) {
                newAdId = rs.getLong(1);
            }
            if (newAdId != 0) {
                ad.setId(newAdId);
            }

        } catch (SQLException e) {
            e.printStackTrace();//*******idk what a stacktrace is******
        }

        return newAdId;

    }

}
