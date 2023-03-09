import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CambioMoneda {
	
	private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";
    private static final String API_KEY = "30f61cf30e715b3b6163baef";
    private static StringBuilder responseBuilder = new StringBuilder();
    
    public CambioMoneda() throws IOException{
    	
    	URL url = new URL(API_URL + "?api_key=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream stream = connection.getInputStream();
        Scanner scanner = new Scanner(stream);
        
        while (scanner.hasNext()) {
            responseBuilder.append(scanner.next());
        }
        scanner.close();
    	
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
