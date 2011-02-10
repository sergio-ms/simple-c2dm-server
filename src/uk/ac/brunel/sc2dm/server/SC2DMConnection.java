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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Sergio Moyano Serrano
 * Created: 12:42 - 2/06/11
 */
public class SC2DMConnection {
	
	private static String DEFAULT_URL = "https://android.apis.google.com/c2dm/send";
	private static String METHOD_POST = "POST";
	
	private String method = null;
	private URL url = null;
	private HttpURLConnection connection = null;
	private boolean connected = false;
	
	public SC2DMConnection (String accountType, String senderId,
		     String password, String service, String source )
	{
		try {
			this.url = new URL(DEFAULT_URL);
			this.method = METHOD_POST;
			SC2DMAuthToken.setParams(accountType, senderId, password, service, source);
			SC2DMAuthToken.authenticate();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SC2DMConnection (String url, String method, String accountType, String senderId,
		     String password, String service, String source)
	{
		try {
			this.url = new URL(url);
			this.method = method;
			SC2DMAuthToken.setParams(accountType, senderId, password, service, source);
			SC2DMAuthToken.authenticate();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void openConnection(){
		
		try {
			if(SC2DMAuthToken.getAuthToken() == null){
				SC2DMAuthToken.authenticate();
			}
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Authorization", "GoogleLogin auth=" + SC2DMAuthToken.getAuthToken());
			connected = true;
		} catch (MalformedURLException e) {
			closeConnection();
			e.printStackTrace();
		} catch (ProtocolException e) {
			closeConnection();
			e.printStackTrace();
		} catch (IOException e) {
			closeConnection();
			e.printStackTrace();
		}

	}
	
	public HttpURLConnection getHttpURLConnection()
	{
		return connection;
	}
	
	public void closeConnection(){
		connection.disconnect();
		connection = null;
		connected = false;
	}
	
	public boolean isConnected(){
		return connected;
	}
}
