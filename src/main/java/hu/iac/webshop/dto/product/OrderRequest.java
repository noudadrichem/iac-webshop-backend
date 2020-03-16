package hu.iac.webshop.dto.product;

import java.util.Date;

public class OrderRequest {
    private Date date;
    private double totalPrice;

    public OrderRequest() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
