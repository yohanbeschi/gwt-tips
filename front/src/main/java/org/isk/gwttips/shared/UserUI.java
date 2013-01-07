package org.isk.gwttips.shared;

import java.io.Serializable;

/**
 * Immutable UserUI Bean.
 * 
 * @author Yohan Beschi
 */
public class UserUI implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;

    public UserUI() {
        super();
    }

    public UserUI(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public final String getFirstname() {
        return firstname;
    }

    public final String getLastname() {
        return lastname;
    }
}
