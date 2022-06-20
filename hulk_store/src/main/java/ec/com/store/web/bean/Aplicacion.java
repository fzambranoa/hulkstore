/**
 * 
 */
package ec.com.store.web.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import ec.com.expert.utils.encript.EncryptMDSha;
import ec.com.expert.utils.enums.AlgorithmEnum;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.manager.Inicializador;
import ec.com.store.model.manager.UsuarioController;
import ec.com.store.web.commons.BaseBean;

/**
 * @author fxavi
 *
 */
@Named("app")
@SessionScoped
public class Aplicacion extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -641107031778912461L;
	
	private UsuarioController userCtrl;
	
	private Usuario usuario;
	
	private String alias;
	
	private String password;
	
	public Aplicacion() {
		userCtrl = new UsuarioController();
	}
	@PostConstruct
	public void inicializarDatos(ComponentSystemEvent event) {
		Inicializador.generarProductos();		
		Inicializador.generarUsuario();
	}
	
	public void ingresar() {
		if(null != alias && !alias.isEmpty() && null != password && !password.isEmpty()) {
			try {
				usuario = userCtrl.loginUsuario(alias, EncryptMDSha.getStringMessageDigest(alias+password, AlgorithmEnum.MD5.getValue()));
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(null != usuario) {
				addMessage("Bienvenido "+usuario.getNombre()+" "+usuario.getApellido());
			}else {
				addError("Los Datos Ingresados no corresponden a un usuario valido, verifique sus credenciales.");
			}
		}else {
			addError("Los campos usuario y Contraseña son requeridos");
		}
		
		PrimeFaces.current().executeScript("PF('login_dialog').hide()");
		PrimeFaces.current().ajax().update("header_form");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioController getUserCtrl() {
		return userCtrl;
	}

	public void setUserCtrl(UsuarioController userCtrl) {
		this.userCtrl = userCtrl;
	}

}
