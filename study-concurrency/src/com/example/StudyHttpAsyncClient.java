package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyHttpAsyncClient {
	private static AtomicInteger counter = new AtomicInteger();
	private static final String BINANCE_URL= 
			"https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				                 .uri(URI.create(BINANCE_URL))
				                 .header("Accept", "application/json")
				                 .build();
		long start = System.currentTimeMillis();
		for (var i=0;i<50;++i) {
				client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				      .thenAccept( resp -> {
				    	  System.err.println(resp.body());
				    	  var count = counter.incrementAndGet();
				    	  if( count == 50) {
				    		  long stop = System.currentTimeMillis();
				    		  System.err.println("Duration: "+(stop-start));				    		  
				    	  }
				    	  
				      });
		}	
		TimeUnit.SECONDS.sleep(10);
	}

}
