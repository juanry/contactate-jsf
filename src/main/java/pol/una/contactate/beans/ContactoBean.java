package pol.una.contactate.beans;


import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pol.una.contactate.util.Contacto;

@Named(value = "contactoBean")
@RequestScoped
public class ContactoBean{
    
    private Contacto instance;

    public ContactoBean() {
        this.instance = new Contacto();
    }
    
    public Contacto getInstance() {
        return instance;
    }

    public void setInstance(Contacto instance) {
        this.instance = instance;
    }
    
}
