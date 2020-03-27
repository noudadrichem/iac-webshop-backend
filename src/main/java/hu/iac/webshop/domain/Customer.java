package hu.iac.webshop.domain;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(){

    }

    public Customer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order) {
        if (!this.orders.contains(order)) {
            this.orders.add(order);
        }
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
