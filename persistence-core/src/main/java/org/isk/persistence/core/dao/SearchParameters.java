package org.isk.persistence.core.dao;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The SearchParameters is used to pass search parameters to the DAO layer. 
 * 
 * Its usage keeps 'find' method signatures in the DAO/Service layer simple.
 * 
 * A SearchParameters helps you drive your search in the following areas:
 * <ul>
 * <li>Configure the search mode (EQUALS, LIKE, ...)</li>
 * <li>Pagination: it allows you to limit your search results to a specific range.</li>
 * <li>Allow you to specify ORDER BY and ASC/DESC</li>
 * <li>Enable/Disable case sensitivity</li>
 * <li>Enable/Disable 2d level cache</li>
 * <li>LIKE search against all string values: simply set the searchPattern property</li>
 * <li>Named query: if you set a named query, it will be executed please read src/main/resources/META-INF/orm.xml</li>
 * </ul>
 * 
 * @see GenericDao
 * @see SearchMode
 * @see OrderBy
 * @see NamedQueryUtil
 */
public class SearchParameters implements Serializable {
    static final private long serialVersionUID = 1L;

    private SearchMode searchMode = SearchMode.LIKE;

    // named query related
    private String namedQuery;
    private Map<String, Object> parameters = newHashMap();

    private List<OrderBy> orders = newArrayList();

    // technical parameters
    private boolean caseSensitive = true;

    // Pagination
    private int maxResultsLimit = 500;
    private int maxResults = 500;
    private int firstResult = 0;

    // cache
    private Boolean cacheable = true;
    private String cacheRegion;

    public SearchParameters() {
    }

    public SearchParameters(SearchMode searchMode) {
        Validate.notNull(searchMode, "searchMode must not be null");
        this.searchMode = searchMode;
    }

    // -----------------------------------
    // SearchMode
    // -----------------------------------

    public void setSearchMode(SearchMode searchMode) {
        Validate.notNull(searchMode, "searchMode must not be null");
        this.searchMode = searchMode;
    }

    /**
     * Returns the SearchMode. It defaults to SearchMode.EQUALS.
     * 
     * @param searchMode
     */
    public SearchMode getSearchMode() {
        return searchMode;
    }

    // -----------------------------------
    // Named query support
    // -----------------------------------

    /**
     * Returns true if a named query has been set, false otherwise. When it returns true, the DAO layer will call the namedQuery.
     */
    public boolean hasNamedQuery() {
        return StringUtils.isNotBlank(namedQuery);
    }

    /**
     * Set the named query to be used by the DAO layer. Null by default.
     * 
     * @param namedQuery
     *            the name of the namedQuery.
     */
    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    /**
     * Return the name of the named query to be used by the DAO layer.
     */
    public String getNamedQuery() {
        return namedQuery;
    }

    public SearchParameters withNamedQuery(String namedQuery) {
        setNamedQuery(namedQuery);
        return this;
    }

    /**
     * Set the parameters for the named query.
     */
    public void setNamedQueryParameters(Map<String, Object> parameters) {
        Validate.notNull(parameters, "parameters must not be null");
        this.parameters = parameters;
    }

    /**
     * The parameters associated with the named query, if any.
     */
    public Map<String, Object> getNamedQueryParameters() {
        return parameters;
    }

    /**
     * Return the value of the passed parameter name.
     */
    public Object getNamedQueryParameter(String parameterName) {
        return parameters.get(parameterName);
    }

    // -----------------------------------
    // Case sensitiveness support
    // -----------------------------------

    /**
     * Set the case sensitiveness. Defaults to false.
     * 
     * @param caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean isCaseInsensitive() {
        return !caseSensitive;
    }

    // -----------------------------------
    // Order by support
    // -----------------------------------

    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    public List<OrderBy> getOrders() {
        return orders;
    }

    public void addOrderBy(OrderBy orderBy) {
        Validate.notNull(orderBy, "orderBy must not be null");
        orders.add(orderBy);
    }

    public void clearOrders() {
        orders.clear();
    }

    public <E> List<Order> getJpaOrders(Root<E> root, CriteriaBuilder builder) {
        List<Order> jpaOrders = newArrayList();

        for (OrderBy ob : orders) {
            if (ob.isOrderDesc()) {
                jpaOrders.add(builder.desc(root.get(ob.getColumn())));
            } else {
                jpaOrders.add(builder.asc(root.get(ob.getColumn())));
            }
        }
        return jpaOrders;
    }

    // -----------------------------------
    // Pagination support
    // -----------------------------------

    public void setMaxResults(int maxResults) {
        Validate.isTrue(maxResults > 0, "maxResults must be > 0");
        this.maxResults = Math.min(maxResults, maxResultsLimit);
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setFirstResult(int firstResult) {
        Validate.isTrue(firstResult >= 0, "firstResult must be >= 0");
        this.firstResult = firstResult;
    }

    public int getFirstResult() {
        return firstResult;
    }

    // -----------------------------------
    // Caching support
    // -----------------------------------

    /**
     * Default to true.
     */
    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public boolean hasCacheRegion() {
        return StringUtils.isNotBlank(cacheRegion);
    }

    public void setCacheRegion(String cacheRegion) {
        this.cacheRegion = cacheRegion;
    }

    public String getCacheRegion() {
        return cacheRegion;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}