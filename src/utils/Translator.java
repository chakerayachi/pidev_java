package utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Translator {

    public static String translate(String paragraphe) throws MalformedURLException, IOException {
  
    String s="";
        s=paragraphe.replaceAll("\\s", "");
        URL url = new URL("https://community-onehourtranslation.p.rapidapi.com/mt/translate/text?secret_key=undefined&public_key=undefined&source_language=fr-fr&target_language=en-us&source_content=text%20for%20translation");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            
        }
        
        
        in.close();
        con.disconnect();
        return content.toString();

    }

}
