package hu.iac.webshop.domain;

import java.util.List;

public class Customer extends Account {
    private String name;
    private String phone;
    private String email;
    private Address address;
    private List<Order> orders;
}
