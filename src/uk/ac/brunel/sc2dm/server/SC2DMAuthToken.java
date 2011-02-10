/*
* Copyright 2011 Sergio Moyano Serrano.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package uk.ac.brunel.sc2dm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Sergio Moyano Serrano
 * Created: 11:30 - 2/06/11
 */

public class SC2DMAuthToken  {
	//private static final Logger log = Logger.getLogger(ReceiverServlet.class.getName());
	private static String authToken = null;
	private static String accountType = null;
	private static String senderId = null;
	private static String password = null;
	private static String service = null;
	private static String source = null;
		
	public static void setParams(String accountType, String senderId,
			     String password, String service, String source ){
		SC2DMAuthToken.accountType = accountType; 
		SC2DMAuthToken.senderId = senderId;
		SC2DMAuthToken.password = password;
		SC2DMAuthToken.service = service;
		SC2DMAuthToken.source = source;		
	}
	
	public static void authenticate(){
    	System.out.println("Obtaining the Google C2DM Client Login token.");
    	
		try {
			
			StringBuilder sb = new StringBuilder();
        	addEncodedParameter(sb, "accoutType", accountType);
        	addEncodedParameter(sb, "Email", senderId);
        	addEncodedParameter(sb, "Passwd", password);
        	addEncodedParameter(sb, "service", service);
        	addEncodedParameter(sb, "source", source);
    
        	String data = sb.toString();
				
            URL url;
			url = new URL("https://www.google.com/accounts/ClientLogin");
            			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.close();
                    	
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     
                      
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	System.out.println("OK");
            	String decodedString;

           		while ((decodedString = in.readLine()) != null) {
           			System.out.println(decodedString);
           			if(decodedString.startsWith("Auth")){
           				authToken = decodedString.substring(5);  
           			}
           		}
            } else {
            	System.out.println("ERROR");
                
            	String decodedString;
           		while ((decodedString = in.readLine()) != null) {
           			System.out.println(decodedString);
           		}
            }
       		in.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// ...
			}
    }
    
    public static String getAuthToken(){
    	return authToken;
    }
    public static void setAuthToken( final String authToken){
    	SC2DMAuthToken.authToken = authToken;
    }
    
    
    public static void addEncodedParameter(StringBuilder sb, String name, String value) 
	{
        if (sb.length() > 0) {
           sb.append("&");
        }
        try {
    	        sb.append(URLEncoder.encode(name, "UTF-8"));
    	        sb.append("=");
    	        sb.append(URLEncoder.encode(value, "UTF-8"));
    	} catch (UnsupportedEncodingException e) {
    		        // Exception handling
    	}
    }   
}
