package ec.com.store.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.ResponsiveOption;

import ec.com.expert.utils.date.DateUtils;
import ec.com.store.model.entity.OrdenCompra;
import ec.com.store.model.entity.OrdenCompraDetalle;
import ec.com.store.model.entity.Producto;
import ec.com.store.model.manager.OrdenCompraController;
import ec.com.store.model.manager.ProductoController;
import ec.com.store.web.commons.BaseBean;
import ec.com.store.web.scope.StoreScope;

@Named("store")
@ViewScoped
public class Tienda extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4867193917439388552L;
	
	@Inject
	StoreScope scope;
	
	@Inject
	Aplicacion app;
	
	private ProductoController productoCtrl;
	
	private OrdenCompraController ordenCtrl;
	
	private String modo;
	
	private OrdenCompra orden;
	
	private List<OrdenCompraDetalle> detallesOrden;
	
	private List<ResponsiveOption> responsiveOptions;
	
	private List<Producto> productos;
	
	public Tienda() {
		super();
		productoCtrl = new ProductoController();
		setModo("LISTA");
	}
	
	public void reset() {
		setModo("LISTA");
		setOrden(null);
		setDetallesOrden(null);
	}
	
	@PostConstruct
	public void inicializar() {
		productos = scope.getProductos();
		ordenCtrl = new OrdenCompraController();
		responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
	}
	
	public void agregarProducto(Producto prd) {
		if(Boolean.TRUE.equals(verificaDisponibilidad(prd.getId()))) {
			detallesOrden = null != detallesOrden ? getDetallesOrden() : new ArrayList<>();
			
			OrdenCompraDetalle orden = new OrdenCompraDetalle();
			orden.setProducto(prd);
			orden.setCantidad(1D);
			orden.setPrecioUnitario(prd.getPrecio());
			
			if(!detallesOrden.contains(orden)) {
				detallesOrden.add(orden);
			}
		}
	}
	
	private Boolean verificaDisponibilidad(Integer id) {
		Boolean valida = Boolean.FALSE;
		Double valor = null;
		try {
			valor = productoCtrl.obtenerDisponibildadProducto(id);
			valida = valor > 0? Boolean.TRUE:Boolean.FALSE;
		}catch (Exception e) {
			e.printStackTrace();
			addWarnings("El producto seleccionado no se encuentra disponible.");
		}
		return valida;
	}
	
	public void procesarOrden() {
		if(null != app.getUsuario()){
			generarValoresOrden();
			setModo("PAGO");
			PrimeFaces.current().ajax().update("store_form");
		}else{
			PrimeFaces.current().executeScript("PF('login_dialog').show()");
			PrimeFaces.current().ajax().update("login_dialog");
		}
	}

	public void generarValoresOrden() {
		orden = new OrdenCompra();
		orden.setEstado("CREADA");
		orden.setFechaCreacion(DateUtils.getCurrentDate());
		orden.setUsuario(app.getUsuario());
		orden.setRazonSocial(app.getUsuario().getNombre()+" "+app.getUsuario().getApellido());
		orden.setUsuarioCrea(app.getUsuario().getId());
		orden.setIdentificacionCompra(app.getUsuario().getIdentificacion());
		//CALCULO DE PRUEBA
		orden.setValorTotal(detallesOrden.stream().mapToDouble(OrdenCompraDetalle::getValorTotal).sum());
		orden.setValorImpuesto(orden.getValorTotal()/1.12);
		orden.setSubTotal(orden.getValorTotal()-orden.getValorImpuesto());
		orden.setDetalles(detallesOrden);
	}
	
	public void confirmarOrden() {
		try {	
			ordenCtrl.guardarOrdenCompra(orden);
			
			addMessage("Su orden fue creada exitosamente.");
			reset();
			PrimeFaces.current().ajax().update("store_form");
		}catch (Exception e) {
			e.printStackTrace();
			
			addError("No fue posible procesar su orden.");
		}
	}
	
	public void cambiarModo(String valor) {
		setModo(valor);
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public ProductoController getProductoCtrl() {
		return productoCtrl;
	}

	public void setProductoCtrl(ProductoController productoCtrl) {
		this.productoCtrl = productoCtrl;
	}

	public List<ResponsiveOption> getResponsiveOptions() {
		return responsiveOptions;
	}

	public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
		this.responsiveOptions = responsiveOptions;
	}

	public List<OrdenCompraDetalle> getDetallesOrden() {
		return detallesOrden;
	}

	public void setDetallesOrden(List<OrdenCompraDetalle> detallesOrden) {
		this.detallesOrden = detallesOrden;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public OrdenCompra getOrden() {
		return orden;
	}

	public void setOrden(OrdenCompra orden) {
		this.orden = orden;
	}

	public OrdenCompraController getOrdenCtrl() {
		return ordenCtrl;
	}

	public void setOrdenCtrl(OrdenCompraController ordenCtrl) {
		this.ordenCtrl = ordenCtrl;
	}
}
