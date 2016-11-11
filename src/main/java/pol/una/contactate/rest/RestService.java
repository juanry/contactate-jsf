package pol.una.contactate.rest;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import pol.una.contactate.util.Contacto;
import pol.una.contactate.util.ContactoList;
/**
 * Clase que provee la concexion con la API.
 * Para llamar a los servicios que este ofrece desde cualquier clase: RestService.metodo()
 * Ejemplo: Crear un nuevo Contacto a, esto seria RestService.persist(a); 
 */
public class RestService {
    
    private static String serviceUrl = "https://desa03.konecta.com.py/pwf/rest/agenda";
   
    /**
     * Metodo que se encarga de obtener todos los contactos
     * @return una clase que contiene la lista y el total de contactos registragos
     * en la API
     */
    public static ContactoList getContactos(){
        try {
            URL url = new URL(serviceUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            Gson gson = new Gson();
            ContactoList response = gson.fromJson(output, ContactoList.class);
            return response;
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ContactoList getContactos(String query){
        try {
            String filtro = serviceUrl + "?filtro="+query;
            URL url = new URL(filtro);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            Gson gson = new Gson();
            ContactoList response = gson.fromJson(output, ContactoList.class);
            return response;
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Metodo que se encarga de actualizar un contacto
     * @param contacto, contacto el cual queremos actualizar
     * @return Retorna el contacto actualizado
     */
    public static Contacto update(Contacto contacto){

    	String updateUrl =  serviceUrl+"/"+String.valueOf(contacto.getId());
        try {
            URL url = new URL(updateUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            Gson gson = new Gson();
            String input = gson.toJson(contacto);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            Contacto response = gson.fromJson(output, Contacto.class);
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo que se encarga de Mostrar toda la info del contacto
     * @param id, recibe el id del contacto que queremos sus detalles
     * @return Contacto,un objeto con toda la info
     */
    public static Contacto detail(Long id){
        String viewUrl = serviceUrl+"/"+String.valueOf(id);
        try {
            URL url = new URL(viewUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            Gson gson = new Gson();
            Contacto response = gson.fromJson(output, Contacto.class);
            return response;
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Metodo que hace la persistencia de un nuevo Contacto
     * @param contacto, el contacto que queremos guardar
     * @return El objeto contacto  creado
     */    
    public static Contacto persist(Contacto contacto) {
        try {
            URL url = new URL(serviceUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            Gson gson = new Gson();
            String input = gson.toJson(contacto);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            Contacto response = gson.fromJson(output, Contacto.class);
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo que se encarga del borrar el contacto
     * @param id, el id del contacto que queremos borrar 
     */
    public static void remove(Long id){
        String removeUrl = serviceUrl+"/"+String.valueOf(id);
        try {
            URL url = new URL(removeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
