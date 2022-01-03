package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyHttpSyncClient {

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
			var response = 
				client.send(request, HttpResponse.BodyHandlers.ofString());
			System.err.println(response.body());
		}	
		long stop = System.currentTimeMillis();
		System.err.println("Duration: "+(stop-start));
	}

}
