package be.icc.form;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
public class BidForm {

    private long newPrice;
    private Long idProduct;

    public long getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(long newPrice) {
        this.newPrice = newPrice;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}