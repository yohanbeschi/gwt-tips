package org.isk.persistence.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.isk.persistence.core.dao.SearchParameters;
import org.isk.persistence.core.domain.Identifiable;

/**
 * The interface to manipulate entities against a repository
 * @see Identifiable
 * @see SearchParameters
 * @see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/dao.html
 */
public interface Repository<T extends Identifiable<PK>, PK extends Serializable> {

    /**
     * Create a new instance. This method may be convenient when you need to instantiate an entity from components like a Spring Web Flow script.
     *
     * @return a new instance with no property set.
     */
    T getNew();

    /**
     * Get an entity by its id.
     *
     * @param id the primaryKey
     * @return
     */
    T getById(PK id);

    /**
     * Get an entity based on the passed example. If the example has a primary key set, it will be used. Otherwise
     * if the example has unique field set, it will be used to lookup the entity.
     *
     * @param example
     * @return the matching entity or null if none could be found.
     */
    T get(T example);

    /**
     * Refresh the passed entity with up to date data.
     *
     * @param entity the entity to refresh.
     */
    void refresh(T entity);

    /**
     * Save or update the passed entity.
     *
     * @param entity the entity to save or update.
     */
    void save(T entity);

    /**
     * Merge the state of the given entity into the
     * current persistence context.
     *
     * @param entity the entity to merge.
     */
    T merge(T entity);

    /**
     * Delete from the repository the entity identified by the passed primary key.
     *
     * @param id the primaryKey identifying the entity to delete.
     */
    void deleteById(PK id);

    /**
     * Delete from the repository the passed entity.
     *
     * @param entity The entity to delete.
     */
    void delete(T entity);
    
    /**
     * Delete from the repository the passed entities.
     * 
     * @param entities The entities to delete.
     */
    void deleteAll(Collection<T> entities);

    /**
     * Find the unique entity matching the passed example.
     *
     * @param example
     * @return the unique entity found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if no entity or more than one entity is found.
     */
    T findUnique(T example);

    /**
     * Find the unique entity matching the passed example.
     *
     * @param example
     * @return the unique entity found or null if none could be found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if more than one entity is found.
     */
    T findUniqueOrNone(T example);

    /**
     * Load all the entities, up to a certain limit. Implementation could for example load at most 500 entities.
     *
     * @return the list of matching entities or an empty list when no entity is found.
     */
    List<T> find();

    /**
     * Load all the entities matching the passed example.
     *
     * @param example
     * @return the list of matching entities or an empty list when no entity is found.
     */
    List<T> find(T example);

    /**
     * @return the number of entities that  {@link #find()} would return.
     */
    int findCount();

    /**
     * @return the number of entities that {@link #find(Identifiable)} would return.
     */
    int findCount(T example);
}