package org.isk.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.isk.persistence.core.domain.Identifiable;

@Entity
@Table(name = "R_USER")
@SequenceGenerator(name = "SEQ_GWTTIPS_ID", sequenceName = "SEQ_GWTTIPS_ID")
public class User implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = -10980236761484679L;

    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String email;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GWTTIPS_ID")
    @Column(name = "USR_ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return this.getId() != null;
    }

    @Column(name = "USR_LOGIN", unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "USR_PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USR_LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "USR_FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "USR_EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
