import java.io.Serializable;

// this is our Bean
// same as our columns in our db
public class Product implements Serializable {
    // id
    private long id;

    // title
    private String title;

    // priceInCents
    private long priceInCents;

    // description
    private String description;

    // 1. Create our zero-argument constructor
    public Product() {
    }// we're doing this so that the JVM can reserve space for this in memory

    // 2. Getters and Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
