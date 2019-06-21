package be.icc.form;

import java.io.Serializable;

/**
 * Created by Scohier Dorian on 27-05-19.
 */
public class FilterPurchasesForm implements Serializable {

    private boolean currentAuctions = true;

    public boolean isCurrentAuctions() {
        return currentAuctions;
    }

    public void setCurrentAuctions(boolean currentAuctions) {
        this.currentAuctions = currentAuctions;
    }
}