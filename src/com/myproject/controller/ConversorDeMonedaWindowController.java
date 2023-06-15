package com.myproject.controller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class ConversorDeMonedaWindowController {
	
	private static final String CONFIG_FILE = "config.properties";
	private static final String API_KEY_PROPERTY = "api.key";
	private static final String API_URL_PROPERTY = "api.url";
    private static StringBuilder responseBuilder = new StringBuilder();
    
    public ConversorDeMonedaWindowController() {
    	
    	String API_KEY = getProperty(API_KEY_PROPERTY);
    	String API_URL = getProperty(API_URL_PROPERTY);
    	
    	URL url;
    	HttpURLConnection connection;
    	InputStream stream;
    	Scanner scanner;
    	
		try {
			url = new URL(API_URL + "?api_key=" + API_KEY);
			connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        stream = connection.getInputStream();
	        scanner = new Scanner(stream);
	        while (scanner.hasNext()) {
	            responseBuilder.append(scanner.next());
	        }
	        scanner.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static String getProperty(String property) {
    	
    	InputStream input = ConversorDeMonedaWindowController.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    	Properties properties = new Properties();
    	try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return properties.getProperty(property);
    	
    }
    
    public double getCambioMoneda (String moneda) {
    	
    	if (moneda.equals("USD")) {
    	
	    	return 1;
        
    	} else {
    		
    		String response = responseBuilder.toString();
	        int start = response.indexOf(moneda) + 5;
	        int end = response.indexOf(",", start);
	        return Double.parseDouble(response.substring(start, end));
	        
    	}
    	
    }
    
    

}
