package no.hvl.dat100.jplab11.oppgave7;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {
	
	public static void main(String[] args) {
		
		int serverport = Configuration.SERVERPORT;
			
		System.out.println("TCP server starting # " + serverport);
		
		try (ServerSocket welcomeSocket = new ServerSocket(serverport)) {
				
			BloggApp server = new BloggApp(welcomeSocket);

			int n = Configuration.N;
			
			while (n>0) {
				
				server.process();
			
				n--;
			}
			
		} catch (IOException ex) {
			System.out.println("TCP server: " + ex.getMessage());
			ex.printStackTrace();
		} 
		
		System.out.println("TCP Server shutting down");
	}
}
