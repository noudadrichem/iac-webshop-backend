package hu.iac.webshop.dto.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddressRequest {
    @NotNull(message = "Please provide a street")
    @Length(max = 255)
    private String street;
    @NotNull(message = "Please provide a city")
    @Length(max = 255)
    private String city;
    @NotNull(message = "Please provide a state")
    @Length(max = 255)
    private String state;
    @NotNull(message = "Please provide a postalCode")
    @Length(max = 255)
    private String postalCode;
    @NotNull(message = "Please provide a country")
    @Length(max = 255)
    private String country;
    @NotNull(message = "Please provide a customerId")
    @Min(0)
    private Long customerId;

    public AddressRequest() {}

    public AddressRequest(
        String street,
        String city,
        String state,
        String postalCode,
        String country
    ) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
