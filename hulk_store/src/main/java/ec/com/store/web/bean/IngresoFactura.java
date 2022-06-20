package ec.com.store.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.expert.utils.date.DateUtils;
import ec.com.store.model.entity.FacturaCompra;
import ec.com.store.model.entity.FacturaCompraDetalle;
import ec.com.store.model.entity.Producto;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.manager.FacturaCompraController;
import ec.com.store.model.manager.ProductoController;
import ec.com.store.web.commons.BaseBean;

@Named("compra")
@ViewScoped
public class IngresoFactura extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4773593087438304276L;
	
	@Inject
	Aplicacion app;
	
	private ProductoController productoCtrllr;
	
	private FacturaCompraController facturaCtrllr;
	
	private FacturaCompra facturaCompra;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String modo;
	
	private List<FacturaCompraDetalle> detallesCompra;
	
	private List<FacturaCompra> reporte;
	
	private List<Producto> productos;
	
	public void reset() {
		modo = "REGISTRO";
		facturaCompra = new FacturaCompra();
		detallesCompra = null;
	}
	
	@PostConstruct
	public void inicializar() {
		reset();
		productoCtrllr = new ProductoController();
		facturaCtrllr = new FacturaCompraController();
		productos = productoCtrllr.listarProductos();
	}
	
	public void agregarDetalle() {
		detallesCompra = null != detallesCompra && !detallesCompra.isEmpty()?
							getDetallesCompra() : new ArrayList<>();
		
		detallesCompra.add(new FacturaCompraDetalle(0D,0D,0D));
	}
	
	public void eliminarDetalle(FacturaCompraDetalle detalle) {
		detallesCompra.remove(detalle);
	}

	public void registrarCompra() {
		if(validaRequeridos()) {
			try {
				facturaCompra.setDetalles(detallesCompra);
				facturaCtrllr.guardarFacturaCompra(app.getUsuario(), facturaCompra);
				addMessage("Proceso generado exitosamente.");
				reset();
			}catch (Exception e) {
				e.printStackTrace();
				addError("Ha ocurrido un error en el proceso, intentelo mas tarde. ");
			}
		}
	}
	
	public void generarReporteCompras() {
		if(null != fechaInicio && null != fechaFin) {
			try {
				reporte = facturaCtrllr.listarRegistrosFecha(DateUtils.completarHoraFecha(fechaInicio, Boolean.TRUE), DateUtils.completarHoraFecha(fechaInicio, Boolean.FALSE));
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if(null == reporte || reporte.isEmpty()) {
				addWarnings("No se encontraron resultados para los valores ingresados.");
			}
		}else {
			addError("No se han ingresado fechas validas.");
		}
	}
	
	private boolean validaRequeridos() {
		Integer error = 0;
		if(null == detallesCompra || detallesCompra.isEmpty()) {
			error++;
			addError("No se han agregado detalles a la compra.");
		}
		
		if(null == facturaCompra.getNumeroDocumento() || facturaCompra.getNumeroDocumento().isEmpty()) {
			error++;
			addError("No se han agregado detalles a la compra.");
		}
		
		if(null == facturaCompra.getImporteTotal() || facturaCompra.getImporteTotal() <= 0) {
			error++;
			addError("El valor importe total debe ser mayor a cero");
		}
		
		if(null == facturaCompra.getImpuestoTotal() || facturaCompra.getImpuestoTotal() <= 0) {
			error++;
			addError("El valor impuesto total debe ser mayor a cero");
		}
		return error > 0? Boolean.FALSE:Boolean.TRUE;
	}

	public void cambiarModo(String valor) {
		setModo(valor);
	}
	
	public FacturaCompra getFacturaCompra() {
		return facturaCompra;
	}

	public void setFacturaCompra(FacturaCompra facturaCompra) {
		this.facturaCompra = facturaCompra;
	}

	public List<FacturaCompraDetalle> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<FacturaCompraDetalle> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public ProductoController getProductoCtrllr() {
		return productoCtrllr;
	}

	public void setProductoCtrllr(ProductoController productoCtrllr) {
		this.productoCtrllr = productoCtrllr;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public FacturaCompraController getFacturaCtrllr() {
		return facturaCtrllr;
	}

	public void setFacturaCtrllr(FacturaCompraController facturaCtrllr) {
		this.facturaCtrllr = facturaCtrllr;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<FacturaCompra> getReporte() {
		return reporte;
	}

	public void setReporte(List<FacturaCompra> reporte) {
		this.reporte = reporte;
	}
}
