package ec.com.store.model.transaction;

import java.util.List;

import javax.persistence.TypedQuery;

import org.h2.command.dml.ExecuteProcedure;
import org.hibernate.HibernateException;

import ec.com.store.model.entity.Usuario;
import ec.com.store.model.util.HibernateUtil;

public class UsuarioService extends HibernateTransaction<Usuario>{

	public Usuario obtenerUsuario() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() throws HibernateException{
		List<Usuario> resultado = null;
		
		session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			
			TypedQuery<Usuario> query = session.createQuery("FROM Usuario ");
			resultado = query.getResultList();
			
			session.getTransaction().commit();
		}catch (Exception e) {
			throw new HibernateException(e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public Usuario loginUsuario(String alias, String password) throws HibernateException{
		Usuario usuario = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT u ");
		sql.append("FROM Usuario u ");
		sql.append("WHERE u.activo = :pActivo ");
		sql.append("AND u.alias = :pAlias ");
		sql.append("AND u.password = :pPassword ");
		
		try {
			session = getSession();
			session.beginTransaction();
			
			TypedQuery<Usuario> query = session.createQuery(sql.toString())
										.setParameter("pActivo", Boolean.TRUE)
										.setParameter("pAlias", alias)
										.setParameter("pPassword", password);
			
			usuario = query.getSingleResult();
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new HibernateException(e);
		}
		return usuario;
	}
}
