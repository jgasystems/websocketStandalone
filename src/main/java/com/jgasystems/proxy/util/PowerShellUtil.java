package com.jgasystems.proxy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class PowerShellUtil {
	
	/**
	 * Ejecuta comando ingresados en powershell
	 * @param command
	 * @return
	 */
	public String runCommand(String command) {
		Runtime runtime = Runtime.getRuntime();
		StringBuilder commands = new StringBuilder();
		commands.append("powershell.exe "); 
		commands.append(command); //$PSVersionTable.PSVersion
		StringBuilder result = new StringBuilder();
		 try
		 {
			 Process process = runtime.exec(commands.toString());
			 process.getOutputStream().close();
			 
			 
			 //Procesar resultado
			 String line;
			 BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			 while ((line = stdout.readLine()) != null){
				 System.err.println("PowerShellUtil:Respuesta: " + line);
				 result.append(line);
			 }
			 stdout.close();
			 //result.append("\n-----\n");
			 
			 //mensaje en caso de error
			 BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			 while ((line = stderr.readLine()) != null){
				 System.err.println("PowerShellUtil:MsgError: " + line);
				 //result.append(line);
			 }
			 stderr.close();
			 //result.append("\n-----\n");

			 TimeUnit.SECONDS.sleep(2);
			 return result.toString();
			 
			 
		 }
		 catch(IOException ex){
			 System.err.println("Error");
			 return null;
		 } catch (InterruptedException e) {
			 System.err.println("Error");
			 return null;
		 }
		
	}
	
	/**
	 * Ejecuta comando de powershell que vengan en un archivo
	 * @param commandFile
	 * @return
	 */
   public String runCommandFile(String commandFile) {
	   Runtime runtime = Runtime.getRuntime();
	   StringBuilder command = new StringBuilder();
	   StringBuilder result = new StringBuilder();
	   command.append("powershell.exe "); 
	   command.append(commandFile); //C:\\Users\\juan\\Desktop\\ejecutar.ps1"
	   try{
		   Process process = runtime.exec(command.toString());
		   process.getOutputStream().close();
		   String line;
		   BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		   while ((line = stdout.readLine()) != null){
			   System.err.println(line);
			   result.append(line);
		   }
		   
		   	TimeUnit.SECONDS.sleep(2);
		   	return result.toString();
	   }
	   catch(IOException ex){
		   System.err.println("Error");
		   return null;
	   } catch (InterruptedException e) {
		   System.err.println("Error");
		   return null;
	   }
	}
	
	

}
