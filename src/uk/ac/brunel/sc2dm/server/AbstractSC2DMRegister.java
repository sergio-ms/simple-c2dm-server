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
 * Created: 20:00 - 2/06/11
 */

public abstract class AbstractSC2DMRegister  extends HttpServlet  { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String userAccount;
	private static String registrationId;
	private SC2DMRegistrationCallbackHandler callbackHandler;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		userAccount =  req.getParameter("userAccount");
        registrationId = req.getParameter("registrationId");
        
        //callbackHandler = new SC2DMDefaultRegistrationCallbackHandler(); 
		callbackHandler.registerDevice(userAccount, registrationId);
	}
	
	public static String getUserAccount()
	{
		return userAccount;
	}
	
	public static String getRegistrationId(){
		return registrationId;
	}
	
	public void setCallbackHandler(final SC2DMRegistrationCallbackHandler callbackHandler)
	{
		this.callbackHandler = callbackHandler;
	}
	

}
