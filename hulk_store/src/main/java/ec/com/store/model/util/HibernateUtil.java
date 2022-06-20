package ec.com.store.model.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static Configuration configuration;
	
	private HibernateUtil() {}
	
	public static SessionFactory getSessionFactory() {
		
		try {
			registry = new StandardServiceRegistryBuilder().applySettings(generarPropiedades()).configure().build();

//				MetadataSources sources = new MetadataSources(registry);
//				Metadata metadata = sources.getMetadataBuilder().build();

			sessionFactory = configuration.buildSessionFactory(registry);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (registry != null) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
		return sessionFactory;
	}
	
	private static Properties generarPropiedades() {
		configuration = new Configuration();
		
		configuration.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		configuration.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.url","jdbc:h2:mem:store");
		
		configuration.setProperty("hibernate.show_sql","true");
		configuration.setProperty("hibernate.format_sql","false");
		configuration.setProperty("hibernate.current_session_context_class","thread");
		configuration.setProperty("hibernate.connection.release_mode","auto");
		configuration.setProperty("hibernate.connection.provider_class","org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
		configuration.setProperty("hibernate.hbm2ddl.auto","create-drop");
		configuration.setProperty("c3p0.unreturnedConnectionTimeout","600");
		configuration.setProperty("c3p0.debugUnreturnedConnectionStackTraces","true");
		configuration.setProperty("c3p0.acquire_increment","1");
		configuration.setProperty("c3p0.idle_test_period","600");
		configuration.setProperty("c3p0.max_size","30");
		configuration.setProperty("c3p0.max_statements","5");
		configuration.setProperty("c3p0.min_size","5");
		configuration.setProperty("c3p0.timeout","600");
		configuration.setProperty("c3p0.checkoutTimeout","6000000");
		configuration.setProperty("c3p0.testConnectionOnCheckout","false");
		configuration.setProperty("c3p0.testConnectionOnCheckin","true");		
		
		configuration.setProperty("hibernate.connection.username","sa");
		configuration.setProperty("hibernate.connection.password","");
		
		return configuration.getProperties();
	}
	
	public static Session getCurrentSession() {
		if (sessionFactory == null) {
			getSessionFactory();			
		}
		
		return sessionFactory.getCurrentSession();
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}