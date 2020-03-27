package hu.iac.webshop.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name="Discount")
public class Discount {

    @Id
    @SequenceGenerator(name = "discount_id_generator", sequenceName = "discount_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_id_generator")
    private Long id;
    private Date startDate;
    private Date endDate;
    private double discountedPrice;
    private String adText;

    @ManyToMany
    @JoinTable(
        name = "discount_product",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private List<Product> products;

    public Discount() {}

    public Discount(Date startDate, Date endDate, double discountedPrice, String adText) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountedPrice = discountedPrice;
        this.adText = adText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
