package com.jgasystems.proxy.info;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class SystemInfo {
	
	public void getSystemInfo() {
		System.out.println("Informacion del sistema:");
		System.out.println("Usuario: "+System.getProperty("user.name"));
		System.out.println("Home: "+System.getProperty("user.home"));
		System.out.println("Directorio: "+System.getProperty("user.dir"));
		System.out.println("Arch OS: "+System.getProperty("os.arch"));
		System.out.println("Name OS: "+System.getProperty("os.name"));
		System.out.println("OS version: "+System.getProperty("os.version"));
		System.out.println("Java home: "+System.getProperty("java.home"));
		System.out.println("Java vendor: "+System.getProperty("java.vendor.url"));
		System.out.println("Java version: "+System.getProperty("java.version"));
		
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			String sHostName;
			sHostName = address.getHostName();
			System.out.println("sHostName of my system is := "+sHostName);
			// Cogemos la IP 
			byte[] bIPAddress = address.getAddress();
			
			System.out.println("IP of my system is := "+address.getHostAddress());
			
		} catch (UnknownHostException e) {
			System.err.println("Ocurrio un error al recuperar la IP: " + e);
			e.printStackTrace();
		}
		
		
		
	}

}
