package ec.com.store.model.manager;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import ec.com.store.model.entity.FacturaCompra;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.transaction.FacturaCompraService;

public class FacturaCompraController {

	FacturaCompraService dao = new FacturaCompraService();
	
	public void guardarFacturaCompra(final Usuario usuario, FacturaCompra compra) throws HibernateException{
		dao.guardarFacturaCompra(usuario, compra);
	}
	
	public void actualizarFacturaCompra(final Usuario usuario, FacturaCompra compra) throws HibernateException{
		dao.actualizaFacturaCompra(usuario, compra);
	}
	
	public List<FacturaCompra> listarRegistrosFecha(Date fechaInicio, Date fechaFin) throws HibernateException{
		return dao.listarRegistrosFecha(fechaInicio, fechaFin);
	}
}