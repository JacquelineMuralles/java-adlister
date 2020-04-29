package models;

import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;
    private static Contacts contactsDao;

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new ListAdsDao();
        }
        return adsDao;
    }

    public static Contacts getContactsDao() throws SQLException {
        if(contactsDao == null) {
            contactsDao = new ContactListDao();
        }
        return contactsDao;
    }
}
