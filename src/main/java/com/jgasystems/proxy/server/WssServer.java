package com.jgasystems.proxy.server;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class WssServer extends WebSocketServer {
	
	private Set<WebSocket> conns;
	private int port;
	
	public WssServer (int port){
	    super(new InetSocketAddress(port));
	    this.port =  port;
	    conns = new HashSet<>();
	  }
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conns.add(conn);
		System.err.println("New Conection from: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
		conn.send("Welcome to the server!"); //This method sends a message to the new client
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		conns.remove(conn);
		System.err.println("Closed Conection from: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.err.println("Message from Client: " +  message);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		if(conn!=null) {
			conns.remove(conn);
		}
		
		System.err.println("Conection error from: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
		System.err.println("Error: " + ex.getMessage());
	}

	@Override
	public void onStart() {
		System.err.println("Server started at port: " + this.port);
		
	}
	

}
