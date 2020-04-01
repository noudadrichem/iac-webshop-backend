package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_generator", sequenceName = "customer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_generator")
    private Long id;
    private String name;
    private String phone;
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Address.class, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private List<Address> addresses = new ArrayList<Address>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Order.class, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private List<Order> orders = new ArrayList<Order>();

    public Customer(){}

    public Customer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addresses = new ArrayList<Address>();
        this.orders = new ArrayList<Order>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Address> getAddress() {
        return addresses;
    }

    public void addAddress(Address address) {
        if (!this.addresses.contains(address)) {
            this.addresses.add(address);
        }
    }
}
