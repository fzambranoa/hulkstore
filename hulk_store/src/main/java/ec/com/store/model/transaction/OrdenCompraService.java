package ec.com.store.model.transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Typed;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ec.com.expert.utils.date.DateUtils;
import ec.com.expert.utils.number.NumberUtils;
import ec.com.store.model.entity.FacturaCompra;
import ec.com.store.model.entity.Kardex;
import ec.com.store.model.entity.OrdenCompra;
import ec.com.store.model.entity.OrdenCompraDetalle;
import ec.com.store.model.entity.Producto;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.util.HibernateUtil;

public class OrdenCompraService extends HibernateTransaction<OrdenCompra>{
	
	public void guardarOrdenCompra(final OrdenCompra orden) throws HibernateException{
		orden.setActivo(Boolean.TRUE);
		orden.setFechaCreacion(DateUtils.getCurrentDate());
		
		try {
			session = getSession();
			session.beginTransaction();
			
			session.saveOrUpdate(orden);
			
			generarValoresKardex(session, orden);
					
			session.getTransaction().commit();
		}catch (Exception e) {
			if(session.getTransaction().isActive()) {	
				session.getTransaction().rollback();
			}
			throw new HibernateException(e);
		}
	}
	
	private void generarValoresKardex(Session session, OrdenCompra orden) {
		List<Kardex> listado = obtenerValoresKardexOrden(session, orden.getDetalles());
		if(null != listado){
			for(Kardex aux:listado) {
				OrdenCompraDetalle tmporden = orden.getDetalles().stream().filter(d -> d.getProducto().getId().equals(aux.getIdProducto())).findFirst().get();
				Kardex tmp = new Kardex();
				tmp.setActivo(Boolean.TRUE);
				tmp.setFechaCreacion(DateUtils.getCurrentDate());
				tmp.setUsuarioCrea(orden.getUsuarioCrea());
				tmp.setDescripcion("Ingreso Orden Numero "+NumberUtils.numberFormat(orden.getId(), "00000"));
				tmp.setCantidad(tmporden.getCantidad());
				tmp.setCostoUnitario(tmporden.getPrecioUnitario());
				tmp.setFechaTransaccion(orden.getFechaCreacion());
				tmp.setIdProducto(aux.getIdProducto());
				tmp.setSaldoCantidad(aux.getCantidad() - tmp.getCantidad());
				tmp.setSaldoCostoUnitario(tmporden.getPrecioUnitario());
				tmp.setSaldoTotal(aux.getSaldoTotal() - tmporden.getValorTotal());
				tmp.setEsIngreso(Boolean.FALSE);
				
				session.saveOrUpdate(tmp);
				
				tmporden.getProducto().setDisponibilidad(tmporden.getProducto().getDisponibilidad() - tmporden.getCantidad());
				session.saveOrUpdate(tmporden.getProducto());
			}
		}else{
			for(OrdenCompraDetalle aux : orden.getDetalles()) {
				Producto tmpprd = aux.getProducto();
				Kardex tmp = new Kardex();
				tmp.setActivo(Boolean.TRUE);
				tmp.setFechaCreacion(DateUtils.getCurrentDate());
				tmp.setUsuarioCrea(orden.getUsuarioCrea());
				tmp.setDescripcion("Ingreso Orden Numero "+NumberUtils.numberFormat(orden.getId(), "00000"));
				tmp.setCantidad(aux.getCantidad());
				tmp.setCostoUnitario(aux.getPrecioUnitario());
				tmp.setFechaTransaccion(orden.getFechaCreacion());
				tmp.setIdProducto(aux.getProducto().getId());
				tmp.setSaldoCantidad(aux.getProducto().getDisponibilidad() - aux.getCantidad());
				tmp.setSaldoCostoUnitario(aux.getPrecioUnitario());
				tmp.setSaldoTotal(aux.getValorTotal());
				tmp.setEsIngreso(Boolean.FALSE);
				
				session.saveOrUpdate(tmp);
				
				tmpprd.setDisponibilidad(tmpprd.getDisponibilidad() - aux.getCantidad());
				session.saveOrUpdate(tmpprd);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<Kardex> obtenerValoresKardexOrden(Session session, List<OrdenCompraDetalle> detalles) {
		List<Kardex> resultado = null;
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Kardex k WHERE k.activo = :pActivo ");
		sql.append("AND k.id IN ( ");
		sql.append("SELECT MAX(k2.id) FROM Kardex k2 WHERE k2.idProducto IN ( ");
		for(int i=0; i < detalles.size();i++){
			sql.append(detalles.get(i).getProducto().getId());
			sql.append(i==detalles.size()-1?") ":", ");
		}
		sql.append("GROUP BY k2.idProducto )");
		
		try {
			TypedQuery<Kardex> query = session.createQuery(sql.toString()).setParameter(":pActivo", Boolean.TRUE);
			
			resultado = query.getResultList();
			
		}catch (Exception e) {
			throw new HibernateException(e);
		}
		return resultado;
	}

	public void actualizaFacturaCompra(final Usuario usuario, FacturaCompra compra) throws HibernateException{
		compra.setUsuarioModifica(usuario.getId());
		compra.setFechaModificacion(Calendar.getInstance().getTime());
		
		try {
			session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			
			session.saveOrUpdate(compra);
					
			session.getTransaction().commit();
		}catch (Exception e) {
			throw new HibernateException(e);
		}
	}
	
	public List<FacturaCompra> listarRegistrosFecha(Date fechaInicio, Date fechaFin) throws HibernateException{
		List<FacturaCompra> resultado;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT f FROM FacturaCompra f ");
		sql.append("JOIN FETCH f.detalles ");
		sql.append("WHERE f.fechaFactura BETWEEN :pFinicio AND :pFfin ");
		
		try {
			session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<FacturaCompra> query = session.createQuery(sql.toString());
			query.setParameter("pFinicio", fechaInicio);
			query.setParameter("pFfin", fechaFin);
			
			resultado = query.getResultList();
			
			session.getTransaction().commit();
		}catch (Exception e) {
			throw new HibernateException(e);
		}
		return resultado;
	}
}

