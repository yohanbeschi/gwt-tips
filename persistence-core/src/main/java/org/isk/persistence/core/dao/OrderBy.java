package org.isk.persistence.core.dao;

import java.io.Serializable;

import static org.isk.persistence.core.dao.OrderByDirection.ASC;
import static org.isk.persistence.core.dao.OrderByDirection.DESC;

import org.apache.commons.lang.Validate;

/**
 * Holder class for search ordering used by the {@link SearchParameters}
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String column;
    private OrderByDirection direction = ASC;

    public OrderBy(String column, OrderByDirection direction) {
        Validate.notNull(column);
        Validate.notNull(direction);
        this.column = column;
        this.direction = direction;
    }

    public OrderBy(String column) {
        Validate.notNull(column);
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}