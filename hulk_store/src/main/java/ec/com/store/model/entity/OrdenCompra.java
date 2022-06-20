package ec.com.store.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDEN")
public class OrdenCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3312157858120998492L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_ORDEN")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "SUBTOTAL")
	private Double subTotal;
	
	@Column(name = "VALOR_IMPUESTO")
	private Double valorImpuesto;
	
	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;
	
	@Column(name = "FORMA_PAGO")
	private String formaPago;
	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "IDENTIFICACION_COMPRA")
	private String identificacionCompra;
	
	@Column(name = "MAIL_COMPRA")
	private String mail;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "ESTADO")
	private String estado;
	
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

	@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
	private List<OrdenCompraDetalle> detalles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getValorImpuesto() {
		return valorImpuesto;
	}

	public void setValorImpuesto(Double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public List<OrdenCompraDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<OrdenCompraDetalle> detalles) {
		this.detalles = detalles;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getIdentificacionCompra() {
		return identificacionCompra;
	}

	public void setIdentificacionCompra(String identificacionCompra) {
		this.identificacionCompra = identificacionCompra;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mailCompra) {
		this.mail = mailCompra;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}