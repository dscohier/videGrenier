package be.icc.form;

import java.io.Serializable;

/**
 * Created by Scohier Dorian on 15-05-19.
 */
public class FilterSalesForm implements Serializable {

    private String[] categories;
    private String[] typeOfSale;
    private String[] sellOrNot;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(String[] typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

    public String[] getSellOrNot() {
        return sellOrNot;
    }

    public void setSellOrNot(String[] sellOrNot) {
        this.sellOrNot = sellOrNot;
    }
}