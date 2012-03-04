package com.brov0010.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.brov0010.dao.util.CreateException;
import com.brov0010.dao.util.DaoException;
import com.brov0010.dao.util.UpdateException;
import com.brov0010.util.QueryMetaData;
import com.brov0010.util.QueryMetaData.SortDirection;
import com.brov0010.util.ResultList;

@SuppressWarnings("all")
public abstract class AbstractDao<T> extends HibernateDaoSupport implements IDao<T> {

	private static final Logger LOG = Logger.getLogger(AbstractDao.class);
	private Class<T> instance;

	/**
	 * This method will put together any specific criteria based on the generic.
	 * 
	 * @return
	 */
	protected final Criteria getCriteria() {
		return getSession().createCriteria(getPersistentClass());
	}
	
	public ResultList<T> scroll(QueryMetaData metaData) {
		Criteria crit = getCriteria();
		
		return scroll(crit, metaData);
	}
	
	/**
	 * Gets a scrollable list that return
	 * 
	 * @param {@link Criteria}
	 * @param {@link QueryMetaData}
	 * 
	 * @return {@link ResultList}
	 */
	public ResultList<T> scroll(Criteria crit, QueryMetaData metaData) {
		
		crit.setCacheMode(CacheMode.IGNORE);
		Number totalCount = 0;
		crit.setProjection(Projections.rowCount());
		totalCount = (Number)crit.list().get(0);
		
		crit.setProjection(null);
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		if(metaData!=null) {
			crit.setFirstResult(metaData.getStart());
			if(metaData.getLimit() > 0) {
				crit.setMaxResults(metaData.getLimit());
			}
			if(metaData.getDir() != null) {
				if(metaData.getDir()==SortDirection.ASC) {
					crit.addOrder(Order.asc(metaData.getSort()));
				}
				if(metaData.getDir()==SortDirection.DESC) {
					crit.addOrder(Order.desc(metaData.getSort()));
				}
			}
		}
		
		ResultList<T> rl = new ResultList<T>();
		rl.addAll(crit.list());
		rl.setTotalCount(totalCount.intValue());
		
		return rl;
	}
	
	public List<T> getAll(){
		return getCriteria().list();
	}

	public List<T> list(Criteria crit) {
		return crit.list();
	}
	
	public T get(int id) {
		return (T) getCriteria().add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	public void delete(T t) throws DaoException {
		try {
			getSession().delete(t);
		} catch (HibernateException e) {
			throw new CreateException(e);
		}
	}
	
	public T update(T t) throws DaoException {
		try {
			getSession().update(t);
		} catch (HibernateException e) {
			throw new UpdateException(e);
		}
		return t;
	}
	
	public void create(T t) throws DaoException {
		try {
			getSession().save(t);
		} catch (HibernateException e) {
			throw new UpdateException(e);
		}
	}
	
	/**
	 * Utility method to flush the session bypassing getting the session
	 */
	public void flush(){
		getSession().flush();
	}
	
	/**
	 * Utility method to evict the object bypassing getting the session. 
	 */
	public void evict(T t) {
		getSession().evict(t);
	}
	
	/**
	 * Returns the Class implementation of the generic class from the extended Dao. 
	 * 
	 * This saves us from having to initialize the class and pass in the representation. 
	 * 
	 */
	protected Class<T> getPersistentClass(){
		if(instance == null) {
			try {
				instance = (Class<T>) ((Class)((ParameterizedType)this.getClass().
					       getGenericSuperclass()).getActualTypeArguments()[0]);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		return instance;
	}
}

