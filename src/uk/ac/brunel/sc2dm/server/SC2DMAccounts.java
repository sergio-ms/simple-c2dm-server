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

import java.util.Hashtable;


/**
 * Default registration callbackhandler.
 * @author Sergio Moyano Serrano (smoyano@correo.ugr.es)
 * Created: 20:00 - 2/06/11
 */
public class SC2DMAccounts {
	private static Hashtable<String, String> clients = new Hashtable<String, String>(); 

	public  static void registerDevice(final String userAccount, final String registrationId){
		clients.put(userAccount, registrationId);
	}
	public  static void unregisterDevice(final String userAccount, final String registrationId){
		clients.remove(userAccount);
	}
	
	public static String getRegistrationId(String userAccount){
		return (String)clients.get(userAccount);
	}
	
	public static boolean isEmpty(){
		return clients.isEmpty();
	}


}
