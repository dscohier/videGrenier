package be.icc.form;

/**
 * Created by Dorian Scohier on 26-06-19.
 */
public class RatingForm {

    private int rating = 1;
    private String description;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}