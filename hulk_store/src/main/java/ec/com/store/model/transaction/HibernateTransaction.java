package ec.com.store.model.transaction;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ec.com.store.model.util.HibernateUtil;

public abstract class HibernateTransaction <T extends Serializable>{

	Session session;
	
	private static final Logger LOGGER = Logger.getLogger(HibernateTransaction.class.getName());
	

	public void saveOrUpdate(T entity) throws HibernateException{
		try {
			session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		}catch (Exception e) {
			if(session.getTransaction().isActive()) {	
				session.getTransaction().rollback();
				LOGGER.log(Level.WARNING, "ERROR SAVE UPDATE, NO FUE POSIBLE REALIZAR ROLLBACK",e);
			}
			throw new HibernateException(e);
		}
	}

	public void delete(T entity) throws HibernateException{
		try{
			session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		}catch(Exception e){
			if(session.getTransaction().isActive()) {	
				session.getTransaction().rollback();
				LOGGER.log(Level.WARNING, "ERROR DELETE, NO FUE POSIBLE REALIZAR ROLLBACK",e);
			}
			throw new HibernateException(e);
		}
	}
	
	public Session getSession() {
		return HibernateUtil.getCurrentSession();
	}
}