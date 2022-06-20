package ec.com.store.model.manager;

import java.util.List;

import ec.com.store.model.entity.Producto;
import ec.com.store.model.transaction.ProductoService;

public class ProductoController {
	
	ProductoService productoDAO = new ProductoService();

	public void guardarProducto(Producto objeto) {
		productoDAO.saveOrUpdate(objeto);
	}
	
	public List<Producto> listarProductos(){
		return productoDAO.listarProductos();
	}
	
	public Double obtenerDisponibildadProducto(Integer idProducto){
		return productoDAO.obtegerDisponibilidadProducto(idProducto);
	}
}