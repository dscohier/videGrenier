package be.icc.form;

/**
 * Created by Dorian Scohier on 28-06-19.
 */
public class SendMessageForm {

    private String content;
    private Long idUserToSend;
    private Long idProduct;

    public Long getIdUserToSend() {
        return idUserToSend;
    }

    public void setIdUserToSend(Long idUserToSend) {
        this.idUserToSend = idUserToSend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}