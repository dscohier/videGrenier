package be.icc.form;

/**
 * Created by Scohier Dorian on 10-05-19.
 */
public class FilterForm {

    private String[] categories;
    String city;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}