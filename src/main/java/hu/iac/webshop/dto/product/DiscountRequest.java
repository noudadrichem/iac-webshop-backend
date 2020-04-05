package hu.iac.webshop.dto.product;

import hu.iac.webshop.domain.Product;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class DiscountRequest {
    @NotNull(message = "Please provide a discountedPrice")
    private double discountedPrice;

    @NotNull(message = "Please provide a adText")
    private String adText;

    @NotNull(message = "Please provide a startDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Please provide a endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private List<String> productIdList;

    public DiscountRequest() {}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }
}
