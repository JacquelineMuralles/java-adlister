// This DAO class will provide access to our Model data, but
    // will not require accessing the ListProductsDao class directly
    // instead, we will reference the Interface
public class DaoFactory {
    private static Products productsDao; //Interface

    public static Products getProductsDao(){
        if(productsDao == null){
            productsDao = new ListProductsDao(); // this is the ONLY reference to the
                    // ListProductDao class
        }
        // if it is not null, return the productsDao that already exists
            // this is an instance of the ListProductsDao class
        return productsDao;
    }

}
