package org.isk.persistence.core.domain;

import java.io.Serializable;

/**
 * By making entities implement this interface we can easily retrieve from the
 * generic DAO the identifier property of the entity.
 */
public interface Identifiable<PK extends Serializable> {

    /**
     * @return the primary key
     */
    PK getId();

    /**
     * Sets the primary key
     */
    void setId(PK id);

    /**
     * Helper method to know whether the primary key is set or not.
     * @return true if the primary key is set, false otherwise
     */
    boolean isIdSet();
}