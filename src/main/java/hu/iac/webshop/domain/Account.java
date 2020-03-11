package hu.iac.webshop.domain;

import java.util.Date;

public class Account {
    private String account;
    private Date createdOn;

    public boolean isActive() {
        return createdOn.before(new Date());
    }
}
