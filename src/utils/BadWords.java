package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptEngine;

public class BadWords {

    private static ScriptEngine scriptEngine;

    private static HttpURLConnection connection;
 
    public static String chackwords(String paragraphe) throws MalformedURLException, IOException {
        String s="";
        s=paragraphe.replaceAll("\\s", "");
        URL url = new URL("https://www.purgomalum.com/service/containsprofanity?text=" + s + "");

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
