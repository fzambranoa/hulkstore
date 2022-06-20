package ec.com.store.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FACTURA_COMPRA_DETALLE")
public class FacturaCompraDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483065735268470902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_FACTURA_COMPRA_DETALLE")
	private Integer id;
	
	@Column(name = "CANTIDAD")
	private Double cantidad;
	
	@Column(name = "PRECIO_UNITARIO")
	private Double precioUnitario;
	
	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;
	
	@Column(name = "ACTIVO")
	private Boolean activo;
	
	@Column(name = "ID_PRODUCTO")
	private Integer idProducto;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "ID_USUARIO_CREA")
	private Integer usuarioCrea;
	
	@Column(name = "ID_USUARIO_MODIFICA")
	private Integer usuarioModifica;
	
	@ManyToOne
	@JoinColumn(name = "ID_FACTURA_COMPRA", nullable = false)
	private FacturaCompra facturaCompra;
	
	public FacturaCompraDetalle() {}
	
	public FacturaCompraDetalle(Double cantidad, Double punitario, Double total) {
		setCantidad(cantidad);
		setPrecioUnitario(punitario);
		setValorTotal(total);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(Integer usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Integer getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public FacturaCompra getFacturaCompra() {
		return facturaCompra;
	}

	public void setFacturaCompra(FacturaCompra facturaCompra) {
		this.facturaCompra = facturaCompra;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
}