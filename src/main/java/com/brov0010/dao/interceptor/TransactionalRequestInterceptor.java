package com.brov0010.dao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Intercepts the HTTP Request and opens a session from hibernate. This is done so that any request
 * will be able to run in a single connection and get rid of opening and closing a connection from
 * the connection pool on every single transaction.
 * 
 * This class has been defined as abstract so that servlet-specific dependencies don't need to be
 * imported into this library. In order to use this class you MUST extend it as there is no way to instantiate it.
 * 
 * It can be used in different servlet libraries. 
 * WARNING: It was designed and has only been tested using the Spring MVC library.
 * 
 * In order to use it for Spring MVC do the following:
 * 
 *  public *YourInterceptorClassName* extends com.pacelabs.data.interceptor.TransactionalRequestInterceptor 
 *  	implements org.springframework.web.servlet.HandlerInterceptor {
 * 
 *   	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
 *			Object arg2, ModelAndView arg3) throws Exception {
 *		} 
 *    
 *  }
 * 
 * postHandle MUST be implemented, but it does not need to contain any logic.
 * 
 * In order to use in other servlet libraries, you will just need to implement the interceptor pattern and call 
 * the following for the before request
 * 
 *  ...
 *  	super.preHandle(HttpServletRequest, HttpServletResponse, null);
 *  ...
 *  
 * after the request
 * 
 *  ...
 *  	super.afterCompletion(HttpServletRequest, HttpServletResponse, null, Exception);
 *  ...
 * 
 * The exception can also be null, but should be trapped so the transaction can be rolled back.
 * 
 * @author chadbrovold
 *
 */
public abstract class TransactionalRequestInterceptor /* implements org.springframework.web.servlet.HandlerInterceptor */ {

	private static final Logger LOG = Logger.getLogger(TransactionalRequestInterceptor.class);
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object handler) throws Exception {
		
		LOG.info("Opening session and beginning transaction.");
		Session sess = sessionFactory.openSession();
		sess.beginTransaction();
		
		if(!TransactionSynchronizationManager.isSynchronizationActive()){
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sess));
			TransactionSynchronizationManager.initSynchronization();
		}
		
		return true;
	}
	
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object handler, Exception ex)
			throws Exception {
		Session sess = sessionFactory.getCurrentSession();
		try {
			if(ex == null) {
				LOG.info("Committing the database transaction.");
				sess.getTransaction().commit();
			} else {
				LOG.warn(ex);
				LOG.info("Rolling back the transaction.");
				sess.getTransaction().rollback();
			}
		} catch (Exception e) {
			LOG.error("There was an error completing the transaction", e);
			sess.getTransaction().rollback();
			
		} finally {
			try {
				if (sess.isOpen()) sess.close();
			} catch (Exception e) { }
			TransactionSynchronizationManager.unbindResource(sessionFactory);
			TransactionSynchronizationManager.clearSynchronization();
		}
	}

}
