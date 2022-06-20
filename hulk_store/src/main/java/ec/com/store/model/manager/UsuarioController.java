package ec.com.store.model.manager;

import ec.com.store.model.entity.Usuario;
import ec.com.store.model.transaction.UsuarioService;

public class UsuarioController {
	
	UsuarioService dao = new UsuarioService();
	
	public Usuario loginUsuario(String alias, String password) {
		return dao.loginUsuario(alias, password);
	}

}
