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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergio Moyano Serrano
 * Created: 21:20 - 2/06/11
 */
public class pushMessage extends HttpServlet  {
	
	
	private final static String accountType = "GOOGLE";
	private final static String senderId = "@gmail.com";
	private final static String password = "";
	private final static String service = "ac2dm";
	private final static String source = "Simple-C2DM-1.0";
	private final static String userAccount = "@gmail.com";
	
	private static String URL = "https://android.apis.google.com/c2dm/send";
	private static String METHOD = "POST";
	
	
	private static final long serialVersionUID = 1L;
	SC2DMConnection connection = null;
	SC2DMessageSender sender = null;
	 
			
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		if(connection == null)
			connection = new SC2DMConnection(URL, METHOD,accountType, senderId, password, service, source);
		if(connection.isConnected() == false)
			connection.openConnection();
		if(sender == null)
			sender = new SC2DMessageSender(connection);

		SC2DMessage msg = new SC2DMessage("Message test", SC2DMAccounts.getRegistrationId(userAccount));
		sender.pushMessage(msg);
		connection.closeConnection();
	
	}	

}
