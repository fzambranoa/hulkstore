package ec.com.store.model.manager;

import org.hibernate.HibernateException;

import ec.com.store.model.entity.OrdenCompra;
import ec.com.store.model.transaction.OrdenCompraService;

public class OrdenCompraController {

	OrdenCompraService dao = new OrdenCompraService();
	
	public void guardarOrdenCompra(OrdenCompra orden) throws HibernateException{
		dao.guardarOrdenCompra(orden);
	}
}