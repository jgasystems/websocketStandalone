package com.jgasystems.proxy.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgasystems.proxy.service.UserMachineInfoService;
import com.jgasystems.proxy.util.PowerShellUtil;

@Component("userMachineInfoService")
public class UserMachineInfoServiceImpl implements UserMachineInfoService {
	
	@Autowired
	PowerShellUtil powerShellUtil;
	
	public Map<String, String> getMachineInfo() {
		Map<String, String> infoUser = new HashMap<String, String>();
		String ipVdi = getVdiIp();
		if("".equals(ipVdi)) {
			//Obtener ip normal
			getIp();
			infoUser.put("isVDI", "0");
		}else {
			infoUser.put("isVDI", "1");
		}
		infoUser.put("MachineIp", ipVdi);
		infoUser.put("User", getUser());
		
		return infoUser;
	}
	
	
	private String getVdiIp(){
		String ipVdi = "";
		ipVdi = powerShellUtil.runCommand("$session = Get-WmiObject -Namespace ROOT\\CITRIX\\EUEM -Class Citrix_Euem_ClientConnect; $clientIpAddress = $session.ClientMachineIP; echo $clientIpAddress ");
		System.err.println("JGA::IPVDI:*" + ipVdi + "*");
		return ipVdi.trim();
	}
	
	private String getUser() {
		String user = "";
		String result  = powerShellUtil.runCommand("whoami");
		String[] valores = result.split("\\\\");
		
		if (valores.length > 1) {
			user = valores[1];
			System.err.println("JGA::user:*" + user + "*");
		}
		
		return user.trim();
	}
	
	
	private String getIp() {
		InetAddress address;
		String ip="";
		try {
			address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
			System.out.println("IP of my system is := "+address.getHostAddress());
			
		} catch (UnknownHostException e) {
			System.err.println("Ocurrio un error al recuperar la IP: " + e);
		}
		
		return ip;
	}

}
