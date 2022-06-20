package ec.com.store.model.test;

import java.util.List;

import ec.com.store.model.entity.Usuario;
import ec.com.store.model.manager.Inicializador;
import ec.com.store.model.transaction.UsuarioService;

public class App {
	
	
    public static void main(String[] args) {
    		Inicializador.generarProductos();
    	
//        Usuario student = new Usuario("fzambrano", EncryptMDSha.getStringMessageDigest("fzambrano"+"fr4nc1sc0", AlgorithmEnum.MD5.getValue()), "SYS", Boolean.TRUE, Calendar.getInstance().getTime(), null);
//        Empleado emp = new Empleado("1717270027","Francisco", "Zambrano", "Sistemas", "mail@mail.com");
//        EmpleadoController ctr = new EmpleadoController();
//        ctr.guardarEmpleado(emp);
//        
//        System.out.println(emp.getActivo());
        
//        
//        Producto producto = new Producto("A", "A", 20.5, null);
//        Producto producto1 = new Producto("B", "B", 20.5, null);
//        Producto producto2 = new Producto("C", "C", 20.5, null);
//        Producto producto3 = new Producto("D", "D", 20.5, null);
//        
//        ProductoController prd = new ProductoController();
//        prd.guardarProducto(producto);
//        prd.guardarProducto(producto1);
//        prd.guardarProducto(producto2);
//        prd.guardarProducto(producto3);
//        
//        List<Producto> lista =  prd.listarProductos();
//        
//        lista.stream().forEach(s -> System.out.println(s.getNombre()));
//        student.setEmpleado(emp);
        
        UsuarioService user = new UsuarioService();
//        user.saveOrUpdate(student);
//        
        List<Usuario> students;
		try {
			students = user.listarUsuarios();
			students.forEach(s -> System.out.println(s.getAlias()));
		} catch (Exception e) {
			e.printStackTrace();
		}
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getCurrentSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // save the student objects
//            session.save(student);
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

//        try (Session session = HibernateUtil.getCurrentSession()) {
//        	session.beginTransaction();
//            List < Usuario > students = session.createQuery("from Usuario", Usuario.class).list();
//            
//            students.forEach(s -> System.out.println(s.getAlias()));
//            
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
    }
}