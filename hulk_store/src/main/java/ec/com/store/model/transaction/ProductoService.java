package ec.com.store.model.transaction;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import ec.com.store.model.entity.Producto;

public class ProductoService extends HibernateTransaction<Producto>{
	
	@SuppressWarnings("unchecked")
	public List<Producto> listarProductos() throws HibernateException{
		List<Producto> resultado = null;
		
		try {
			session = getSession();
			session.beginTransaction();
			
			TypedQuery<Producto> query = session.createQuery("FROM Producto ");
			resultado = query.getResultList();
					
			session.getTransaction().commit();
		}catch (Exception e) {
			throw new HibernateException(e);
		}
		
		return resultado;
	}
	
	public Double obtegerDisponibilidadProducto(Integer idProducto) throws HibernateException{
		Double resultado = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT disponibilidad FROM Producto WHERE id = :pIdProducto");
		try {
			session = getSession();
			session.beginTransaction();

			resultado = (Double) session.createQuery(sql.toString()).setParameter("pIdProducto", idProducto).getSingleResult();
					
			session.getTransaction().commit();
		}catch (Exception e) {
 			throw new HibernateException(e);
		}
		
		return resultado;
	}
}

