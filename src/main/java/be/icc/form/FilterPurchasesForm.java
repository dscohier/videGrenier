package be.icc.form;

/**
 * Created by Scohier Dorian on 27-05-19.
 */
public class FilterPurchasesForm {

    private String[] categories;
    private boolean currentAuctions = true;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public boolean isCurrentAuctions() {
        return currentAuctions;
    }

    public void setCurrentAuctions(boolean currentAuctions) {
        this.currentAuctions = currentAuctions;
    }
}