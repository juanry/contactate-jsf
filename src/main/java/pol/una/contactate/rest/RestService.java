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

public class RestService {
    
    private static String serviceUrl = "https://desa03.konecta.com.py/pwf/rest/agenda";
   
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
}
