package ec.com.store.web.scope;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ec.com.store.model.entity.Producto;
import ec.com.store.model.manager.Inicializador;

@Named("storescope")
@ApplicationScoped
public class StoreScope {
	
	
	List<Producto> productos;
	
	@PostConstruct
	public void init() {
		productos = Inicializador.generarProductos();		
		Inicializador.generarUsuario();
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
