package objects;

import java.math.BigDecimal;

public class Product {
    private String title;
    private String model;
    private BigDecimal cPrice;
    private BigDecimal oPrice;
    private String href;

    public Product (String title, String model, BigDecimal cPrice, BigDecimal oPrice, String href){
        this.title=title;
        this.model=model;
        this.cPrice=cPrice;
        this.oPrice=oPrice;
        this.href=href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getcPrice() {
        return cPrice;
    }

    public void setcPrice(BigDecimal cPrice) {
        this.cPrice = cPrice;
    }

    public BigDecimal getoPrice() {
        return oPrice;
    }

    public void setoPrice(BigDecimal oPrice) {
        this.oPrice = oPrice;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", model='" + model + '\'' +
                ", cPrice=" + cPrice +
                ", oPrice=" + oPrice +
                ", href='" + href + '\'' +
                '}';
    }
}
