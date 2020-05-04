package com.codeup.adlister.dao;

import models.Config;

public class DaoFactory {
    private static Ads adsDao;
    private static Config config = new Config();
    //added userDao
    private static Users userDao;

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    //created a method getUserDao using the userDao
    //and the config from MySqlUsersDao
    public static Users getUserDao(){
        if(userDao == null){
            userDao == new MySqlUsersDao(config);
        }
        return userDao;
    }
}
