package ec.com.store.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FACTURA_COMPRA")
public class FacturaCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483065735268470902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_FACTURA_COMPRA")
	private Integer id;
	
	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;
	
	@Column(name = "FECHA_FACTURA")
	private Date fechaFactura;
	
	@Column(name = "IMPORTE_TOTAL")
	private Double importeTotal;
	
	@Column(name = "IMPUESTO_TOTAL")
	private Double impuestoTotal;
	
	@Column(name = "ACTIVO")
	private Boolean activo;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "ID_USUARIO_CREA")
	private Integer usuarioCrea;
	
	@Column(name = "ID_USUARIO_MODIFICA")
	private Integer usuarioModifica;
	
	@OneToMany(mappedBy = "facturaCompra", cascade = CascadeType.ALL)
	private List<FacturaCompraDetalle> detalles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
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

	public List<FacturaCompraDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<FacturaCompraDetalle> detalles) {
		this.detalles = detalles;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getImpuestoTotal() {
		return impuestoTotal;
	}

	public void setImpuestoTotal(Double impuestoTotal) {
		this.impuestoTotal = impuestoTotal;
	}
}