package be.icc.form;

/**
 * Created by Dorian Scohier on 26-06-19.
 */
public class RatingForm {

    private double rating = 1;
    private String description;
    private Long idUserToRate;
    private boolean isForSeller;

    public boolean getIsForSeller() {
        return isForSeller;
    }

    public void setIsForSeller(boolean forSeller) {
        isForSeller = forSeller;
    }

    public Long getIdUserToRate() {
        return idUserToRate;
    }

    public void setIdUserToRate(Long idUserToRate) {
        this.idUserToRate = idUserToRate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}