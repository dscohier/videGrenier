package be.icc.form;

/**
 * Created by Scohier Dorian on 27-05-19.
 */
public class FilterPurchasesForm {

    private boolean currentAuctions = true;

    public boolean isCurrentAuctions() {
        return currentAuctions;
    }

    public void setCurrentAuctions(boolean currentAuctions) {
        this.currentAuctions = currentAuctions;
    }
}