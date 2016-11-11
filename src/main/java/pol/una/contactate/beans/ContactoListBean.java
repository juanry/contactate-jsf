package pol.una.contactate.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pol.una.contactate.rest.RestService;
import pol.una.contactate.util.ContactoList;

@Named(value = "contactoListBean")
@RequestScoped
public class ContactoListBean {
    
    private ContactoList instance;
    
    public ContactoListBean(){
         this.instance = RestService.getContactos();
    }
    public ContactoList getInstance(){
       System.out.println(instance.getLista().size());
       return instance;
    }

    public void setInstance(ContactoList instance) {
        this.instance = instance;
    }
    
}
