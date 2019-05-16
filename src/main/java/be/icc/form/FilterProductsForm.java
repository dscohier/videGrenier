package be.icc.form;

/**
 * Created by Scohier Dorian on 10-05-19.
 */
public class FilterProductsForm {

    private String[] categories;
    private String city;
    private String country;
    private String[] typeOfSale;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(String[] typeOfSale) {
        this.typeOfSale = typeOfSale;
    }
}