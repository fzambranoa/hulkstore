package ec.com.store.web.commons;

import java.text.MessageFormat;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 
 * @author fzambrano
 *
 */
public abstract class BaseBean {

	/**
	 * Metodo que retorna la instancia actual de JSF
	 * 
	 * @return
	 */
	protected FacesContext context() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obtiene el contexto de la aplicacion
	 * 
	 * @return
	 */
	protected String getContextPath() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest()).getContextPath();
	}
	

	/**
	 * Retorna el request de la aplicacion
	 * 
	 * @return
	 */
	protected HttpServletRequest getHttpServletRequest() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest());
	}

	/**
	 * Retorna la sesion de la aplicacion
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest()).getSession();
	}
	
	/**
	 * Retorna el mapa de parametros
	 * @return
	 */
	protected Map<String, String> getRequestParameterMap(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}
	
	/**
	 * Retorna el mapa de parametros
	 * @return
	 */
	protected Map<String, Object> getSessionMap(){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	/**
	 * Retorna el mapa de parametros
	 * @return
	 */
	protected Map<String, Object> getViewMap(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}
	
	/**
	 * Remueve el Bean de Session
	 * 
	 * @return
	 */
	protected void removeBeanSession(String beanName) {
		
		HttpSession session=getSession();
		session.removeAttribute(beanName);
	}
	
	/**
	 * Obtiene el contexto inicial de la aplicacion
	 * 
	 * @return el contexto de la aplicacion
	 * @throws NamingException
	 */
	public static Context getContext() throws NamingException {
		return new InitialContext();
	}

	/**
	 * Retorna el resource correspondiente al bundle y el key especificados Ej:
	 * getResource("errores","login.incorrecto");
	 * 
	 * @param bundle
	 * @param key
	 * @return
	 */
	protected String getResource(String bundle, String key) {
		return context().getApplication().getResourceBundle(context(), bundle)
				.getString(key);
	}
	
	 /**
     * (1)Retorna un valor desde el bundle asociado a un key de un archivo de propiedades con formato y parametros dados 
     * @param key
     * 			llave a buscar en el archivo de propiedades
     * @param params
     * 			parametros opcionales a sobreescribir en el String
     * @return
     * 			String
     */
    protected String getString(String bundle,String key, Object... params) {
    	ResourceBundle  uiBundle=null;    	
    	try {
   			uiBundle = context().getApplication().getResourceBundle(context(), bundle);
    	} catch(MissingResourceException e) {
    		return "'"+ bundle + "' No se encuentra.";
    	}
    	
        return getString(key, uiBundle, params);
    }
    
    /**
     * Recupera el String de un bundle dado el key
      * @param key
     * 			llave a buscar en el archivo de propiedades
     * @param params
     * 			parametros opcionales a sobreescribir en el String
     * @return
     * 			String
     */
    public static String getString(String key, ResourceBundle bundle, Object...params) {
    	if (key==null || key.isEmpty())
    		return "";
    	try {
    		return MessageFormat.format(bundle.getString(key), params);
    	} catch(MissingResourceException e) {
    		return key+"no encontrada.";
    	}
    }

	/**
	 * Agrega mensajes para mostrar en session
	 * @param summary
	 * 				cabecera del mensaje
	 * @param detalle
	 * 				detalle informativo
	 */
	public void addMessage(String summary, String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	/**
	 * Agrega mensajes para mostrar en session
	 * @param summary
	 * 				cabecera del mensaje
	 * @param detalle
	 * 				detalle informativo
	 */
	public void addMessage(String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	/**
	 * Agrega errores para mostrar en session
	 * @param summary
	 * 				cabecera del error
	 * @param detalle
	 * 				detalle informativo
	 */
	public static void addError(String summary, String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
	
	/**
	 * Agrega errores para mostrar en session
	 * @param summary
	 * 				cabecera del error
	 * @param detalle
	 * 				detalle informativo
	 */
	public static void addError(String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
	
	/**
	 * Agrega advertencias para mostrar en session
	 * @param summary
	 * 				cabecera del error
	 * @param detalle
	 * 				detalle informativo
	 */
	public static void addWarnings(String summary, String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
	
	/**
	 * Agrega advertencias para mostrar en session
	 * @param summary
	 * 				cabecera del error
	 * @param detalle
	 * 				detalle informativo
	 */
	public static void addWarnings(String detalle) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Info",  detalle);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
	
	//Almacena el stackTrace de un error
	public static String getStackTrace(Exception e) {
		String trace = null;
		trace = ExceptionUtils.getStackTrace(e);
		return trace;
	}
}