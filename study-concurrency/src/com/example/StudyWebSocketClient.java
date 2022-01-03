package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

// {"e":"trade",
//  "E":1634281694486,
//  "s":"BTCUSDT","t":1097942067,"p":"59579.69000000","q":"0.00200000","b":7910184530,"a":7910184440,"T":1634281694485,"m":false,"M":true}

public class StudyWebSocketClient {
	private static final String BINANCE_REST_OVER_WS_URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";
	// Reactive Client -> Java 9 -> Flow API (Reactive Programming)
	// Streaming Data -> Compact Representation
	public static void main(String[] args) throws InterruptedException {
		// Domain Event: Trade -> Rest over WebSocket -> [Near] Real-time
		// Event-Driven Architecture -> Event triggered? -> Asynchronous Programming
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create(BINANCE_REST_OVER_WS_URL), new BinanceWebSocketListener());
		TimeUnit.SECONDS.sleep(30);
	}

}

class BinanceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the Binance server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the Binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error has occured: "+error.getMessage()+"!");
	}
	
}