package hu.iac.webshop.dto.product;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CustomerRequest {
    @NotNull(message = "Please provide a name")
    private String name;
    @NotNull(message = "Please provide a phone number")
    private String phone;
    @NotNull(message = "Please provide a email")
    @Email
    private String email;

    public CustomerRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
