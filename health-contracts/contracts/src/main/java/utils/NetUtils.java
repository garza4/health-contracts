package utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class NetUtils {
	
	public String send(String payload,String uri) {
		HttpClient client;
		String ret = null;
		try {
			client = HttpClient.newHttpClient(); 	
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(uri))
					  .POST(HttpRequest.BodyPublishers.ofString(payload))
					  .build();
			
			HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
			ret =  resp.body();
			
		}catch(Exception e) {
			
		}
		return ret;
	}

}
