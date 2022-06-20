package ec.com.store.model.transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ec.com.expert.utils.date.DateUtils;
import ec.com.expert.utils.number.NumberUtils;
import ec.com.store.model.entity.FacturaCompra;
import ec.com.store.model.entity.FacturaCompraDetalle;
import ec.com.store.model.entity.Kardex;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.util.HibernateUtil;

public class FacturaCompraService extends HibernateTransaction<FacturaCompra>{
	
	public void guardarFacturaCompra(final Usuario usuario, FacturaCompra compra) throws HibernateException{
		compra.setUsuarioCrea(usuario.getId());
		compra.setFechaCreacion(Calendar.getInstance().getTime());
		
		try {
			session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			
			session.saveOrUpdate(compra);
			
			generarValoresKardex(session, compra);
					
			session.getTransaction().commit();
		}catch (Exception e) {
			throw new HibernateException(e);
		}
	}
	
	private void generarValoresKardex(Session session, FacturaCompra compra) {
		List<Kardex> listado = obtenerValoresKardexOrden(session, compra.getDetalles());
		if(null != listado){
			for(Kardex aux:listado) {
				FacturaCompraDetalle tmpcompra = compra.getDetalles().stream().filter(d -> d.getIdProducto().equals(aux.getIdProducto())).findFirst().get();
				Kardex tmp = new Kardex();
				tmp.setActivo(Boolean.TRUE);
				tmp.setFechaCreacion(DateUtils.getCurrentDate());
				tmp.setUsuarioCrea(compra.getUsuarioCrea());
				tmp.setDescripcion("Ingreso Compra Numero "+NumberUtils.numberFormat(compra.getId(), "00000"));
				tmp.setCantidad(tmpcompra.getCantidad());
				tmp.setCostoUnitario(tmpcompra.getPrecioUnitario());
				tmp.setFechaTransaccion(compra.getFechaCreacion());
				tmp.setIdProducto(aux.getIdProducto());
				tmp.setSaldoCantidad(aux.getCantidad() + tmp.getCantidad());
				tmp.setSaldoCostoUnitario(tmpcompra.getPrecioUnitario());
				tmp.setSaldoTotal(aux.getSaldoTotal() + tmpcompra.getValorTotal());
				tmp.setEsIngreso(Boolean.TRUE);
				
				session.saveOrUpdate(tmp);
				
				actualizarProducto(session, tmp);
			}
		}else{
			for(FacturaCompraDetalle aux : compra.getDetalles()) {
				Kardex tmp = new Kardex();
				tmp.setActivo(Boolean.TRUE);
				tmp.setFechaCreacion(DateUtils.getCurrentDate());
				tmp.setUsuarioCrea(compra.getUsuarioCrea());
				tmp.setDescripcion("Ingreso Orden Numero "+NumberUtils.numberFormat(compra.getId(), "00000"));
				tmp.setCantidad(aux.getCantidad());
				tmp.setCostoUnitario(aux.getPrecioUnitario());
				tmp.setFechaTransaccion(compra.getFechaCreacion());
				tmp.setIdProducto(aux.getIdProducto());
				tmp.setSaldoCantidad(aux.getCantidad());
				tmp.setSaldoCostoUnitario(aux.getPrecioUnitario());
				tmp.setSaldoTotal(aux.getValorTotal());
				tmp.setEsIngreso(Boolean.TRUE);
				
				session.saveOrUpdate(tmp);
				
				actualizarProducto(session, tmp);
			}
		}
	}

	private void actualizarProducto(Session session, Kardex tmp) {
		session.createSQLQuery("UPDATE PRODUCTO SET DISPONIBILIDAD = DISPONIBILIDAD + "+tmp.getCantidad()+" WHERE ID_PRODUCTO = "+tmp.getIdProducto()).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	private List<Kardex> obtenerValoresKardexOrden(Session session, List<FacturaCompraDetalle> detalles) {
		List<Kardex> resultado = null;
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Kardex k WHERE k.activo = :pActivo ");
		sql.append("AND k.id IN ( ");
		sql.append("SELECT MAX(k2.id) FROM Kardex k2 WHERE k2.idProducto IN ( ");
		for(int i=0; i < detalles.size();i++){
			sql.append(detalles.get(i).getIdProducto());
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

