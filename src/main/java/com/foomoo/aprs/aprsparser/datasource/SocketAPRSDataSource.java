package com.foomoo.aprs.aprsparser.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Required;


public class SocketAPRSDataSource extends AbstractAPRSDataSource {

	private Socket fSocket;
	private String fUser = "Guest";
	private String fPassword = "-1";
	
	@Required
	public void setSocket(Socket aSocket) {fSocket = aSocket;}

	public void setUser(String aUser) {fUser = aUser;}
	public void setPassword(String aPassword) {fPassword = aPassword;}

	@Override
	public void run() {
		
		try {
			InputStream inputStream = fSocket.getInputStream();
			OutputStream outputStream = fSocket.getOutputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = bufferedReader.readLine();

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			
			// need to use something like: user M3DBO pass -1 vers UI-View32 V2.03 filter p/G/M/2
			outputStreamWriter.write("user " + fUser + " pass " + fPassword + " vers homemade 1 filter p/G/M/2\n");
			outputStreamWriter.flush();
			
			while (null != line)
			{
			    if (!line.startsWith("#"))
			    {
			        fireDataReceived(line);
			    }
				
				line = bufferedReader.readLine();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
