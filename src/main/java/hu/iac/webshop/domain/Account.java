package hu.iac.webshop.domain;

import java.util.Date;

public class Account {
    private String userName;
    private long id;
    private String role;
    private String account;
    private Date createdOn;

    public Account() {}

    public Account(String account, Date createdOn) {
        this.account = account;
        this.createdOn = createdOn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return createdOn.before(new Date());
    }
}
