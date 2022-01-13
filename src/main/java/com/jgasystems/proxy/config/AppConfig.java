package com.jgasystems.proxy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.jgasystems.proxy.info.SystemInfo;
import com.jgasystems.proxy.server.WssServer;
import com.jgasystems.proxy.service.UserMachineInfoService;
import com.jgasystems.proxy.service.impl.UserMachineInfoServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.jgasystems.proxy")
@PropertySource("classpath:proxy.properties")
//@PropertySource(value = { "file:/clocal/www/proxy.properties" })
public class AppConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public SystemInfo systemInfo () {
		SystemInfo systemInfo = new SystemInfo();
		System.out.println(env.getProperty("version"));
		System.out.println(env.getProperty("port.wss"));
		//saludator.setMensaje(env.getProperty("mensaje"));
		return systemInfo;
	}
	
	@Bean
	public WssServer wssServer () {
		int puerto= Integer.parseInt(env.getProperty("port.wss"));
		WssServer wssServer = new WssServer(puerto);
		return wssServer;
	}
	
	
	//To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	

}
