package ec.com.store.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KARDEX")
public class Kardex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483065735268470902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_KARDEX")
	private Integer id;
	
	@Column(name = "ID_PRODUCTO")
	private Integer idProducto;
	
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;
	
	@Column(name = "ES_INGRESO")
	private Boolean esIngreso;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "CANTIDAD")
	private Double cantidad;
	
	@Column(name = "COSTO_UNITARIO")
	private Double costoUnitario;
	
	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;
	
	@Column(name = "SALDO_CANTIDAD")
	private Double saldoCantidad;
	
	@Column(name = "SALDO_COSTO_UNITARIO")
	private Double saldoCostoUnitario;
	
	@Column(name = "SALDO_TOTAL")
	private Double saldoTotal;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getSaldoCantidad() {
		return saldoCantidad;
	}

	public void setSaldoCantidad(Double saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}

	public Double getSaldoCostoUnitario() {
		return saldoCostoUnitario;
	}

	public void setSaldoCostoUnitario(Double saldoCostoUnitario) {
		this.saldoCostoUnitario = saldoCostoUnitario;
	}

	public Double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
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

	public Boolean getEsIngreso() {
		return esIngreso;
	}

	public void setEsIngreso(Boolean esIngreso) {
		this.esIngreso = esIngreso;
	}
}