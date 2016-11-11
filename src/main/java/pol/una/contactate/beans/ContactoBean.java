package pol.una.contactate.beans;



import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import pol.una.contactate.rest.RestService;
import pol.una.contactate.util.Contacto;

@ManagedBean(eager=true)
@Named(value = "contactoBean")
@RequestScoped
public class ContactoBean{
    
    private Contacto instance;
    
    
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
    
    
    
}
