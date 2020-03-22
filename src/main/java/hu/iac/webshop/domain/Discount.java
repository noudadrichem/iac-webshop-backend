package hu.iac.webshop.domain;

import javax.persistence.*;
import java.sql.Date;

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

    public Discount() {}

    public Discount(Date startDate, Date endDate, double discountedPrice, String adText) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountedPrice = discountedPrice;
        this.adText = adText;
    }
}
