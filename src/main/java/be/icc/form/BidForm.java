package be.icc.form;

/**
 * Created by Dorian Scohier on 21-02-19.
 */
public class BidForm {

    private double newPrice;
    private Long idProduct;

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}