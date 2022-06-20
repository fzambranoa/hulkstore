package ec.com.store.model.manager;

import java.util.List;

import org.hibernate.HibernateException;

import ec.com.expert.utils.date.DateUtils;
import ec.com.expert.utils.encript.EncryptMDSha;
import ec.com.expert.utils.enums.AlgorithmEnum;
import ec.com.store.model.entity.Producto;
import ec.com.store.model.entity.Usuario;
import ec.com.store.model.transaction.ProductoService;
import ec.com.store.model.transaction.UsuarioService;
import ec.com.store.model.util.UtilitarioInicio;

public class Inicializador {

	static ProductoService prddao = new ProductoService();
	
	static UsuarioService usdao = new UsuarioService();
	
	public static List<Producto> generarProductos() throws HibernateException{
		List<Producto> productos = UtilitarioInicio.generarProductosInicio();
		for(Producto aux : productos) {
			prddao.saveOrUpdate(aux);
		}
		
		return productos;
	}
	
	public static void generarUsuario() throws HibernateException{
        Usuario usuario = new Usuario();
        usuario.setActivo(Boolean.TRUE);
        usuario.setAlias("1717270027");
        usuario.setApellido("ZAMBRANO");
        usuario.setDepartamento("SISTEMAS");
        usuario.setFechaCreacion(DateUtils.getCurrentDate());
        usuario.setIdentificacion("1717270027");
        usuario.setMail("mail@mail.com");
        usuario.setNombre("FRANCISCO");
        usuario.setPassword(EncryptMDSha.getStringMessageDigest(usuario.getAlias()+usuario.getIdentificacion(), AlgorithmEnum.MD5.getValue()));
        usuario.setRol("SYS");
        usuario.setUsuarioCrea(1);
        
        usdao.saveOrUpdate(usuario);
	}
}