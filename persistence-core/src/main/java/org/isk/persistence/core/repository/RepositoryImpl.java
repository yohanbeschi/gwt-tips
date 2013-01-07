package org.isk.persistence.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.isk.persistence.core.dao.GenericDao;
import org.isk.persistence.core.dao.SearchParameters;
import org.isk.persistence.core.domain.Identifiable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of the {@link Repository}
 * @see Repository
 */
public abstract class RepositoryImpl<T extends Identifiable<PK>, PK extends Serializable> implements Repository<T, PK> {

    private Logger log;

    public RepositoryImpl() {
        this.log = Logger.getLogger(getClass());
    }

    abstract public GenericDao<T, PK> getDao();

    /**
     * {@inheritDoc}
     */
    public abstract T getNew();

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void save(T model) {
    	this.getDao().save(model);
    }

    @Transactional
    public T merge(T model) {
        return this.getDao().merge(model);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T getById(PK id) {
        T entity = getNew();
        entity.setId(id);
        return this.get(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void deleteById(PK id) {
    	this.delete(getById(id));
    }
    
    /**
     * {@inheritDoc}
     */
    @Transactional
    public void deleteAll(Collection<T> entities) {
    	if (entities != null) {
	    	for (T entity : entities) {
	    		this.delete(this.getById(entity.getId()));
	    	}
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T get(T model) {
        return getDao().get(model);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void delete(T model) {
        if (model == null) {
            if (log.isDebugEnabled()) {
                log.debug("Skipping deletion for null model!");
            }

            return;
        }

        getDao().delete(model);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public void refresh(T entity) {
        getDao().refresh(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T findUnique(T model) {
        return findUnique(model, new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T findUnique(T model, SearchParameters sp) {
        return getDao().findUnique(model, sp);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T findUniqueOrNone(T model) {
        return findUniqueOrNone(model, new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public T findUniqueOrNone(T model, SearchParameters sp) {
        return getDao().findUniqueOrNone(model, sp);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<T> find() {
        return find(getNew(), new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<T> find(T model) {
        return find(model, new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<T> find(SearchParameters sp) {
        return find(getNew(), sp);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<T> find(T model, SearchParameters sp) {
        return getDao().find(model, sp);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public int findCount() {
        return findCount(getNew(), new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public int findCount(T model) {
        return findCount(model, new SearchParameters());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public int findCount(SearchParameters sp) {
        return findCount(getNew(), sp);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public int findCount(T model, SearchParameters sp) {
        return getDao().findCount(model, sp);
    }
}