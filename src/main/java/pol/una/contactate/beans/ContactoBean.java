package pol.una.contactate.beans;



import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import pol.una.contactate.rest.RestService;
import pol.una.contactate.util.Contacto;

@ManagedBean(eager=true)
@Named(value = "contactoBean")
@RequestScoped
public class ContactoBean{
    
    private Contacto instance = new Contacto();
    
    
    private Long idContacto;
    
    public void init(Long id){
        idContacto = id;
        System.out.println("Esto es una prueba de cargar");
        Contacto instance = RestService.detail(idContacto);
    }
    
    public Contacto getInstance() {
        return instance;
    }
    public void setInstance(Contacto instance) {
        this.instance = instance;
    }

    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }
    public void persist()
	{
		if (RestService.persist(instance)!= null)
		{
			contactoGuardado();
		}
		else
		{
			contactoNoGuardado();
		}
		
	}
    public void contactoGuardado()
    {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Contacto creado con Exito!"));
    }
    public void contactoNoGuardado()
    {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Contacto no pudo ser creado verifique!"));
    }
    public void update()
    {
    	System.out.println(instance.getNombre());
    	
    	if (RestService.update(instance)!= null)
    	{
    		contactoActualizado();
    	}
    	else
    	{
    		contactoNoActualizado();
    	} 
    }
    public void contactoActualizado()
    {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Contacto actualizado con Exito!"));
    }
    public void contactoNoActualizado()
    {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Contacto no pudo ser actualizado verifique!"));
    }
    
    
    
    
    
}
