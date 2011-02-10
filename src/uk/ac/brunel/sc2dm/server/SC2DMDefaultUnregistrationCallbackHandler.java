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


/**
 * Default registration callbackhandler.
 * @author Sergio Moyano Serrano (smoyano@correo.ugr.es)
 * Created: 20:30 - 2/06/11
 */
public class SC2DMDefaultUnregistrationCallbackHandler implements SC2DMUnregistrationCallbackHandler {
	 

	public  void unregisterDevice(final String userAccount, final String registrationId){
		System.out.println("userAccount:" + userAccount);
		System.out.println("devideId:" + registrationId);
		SC2DMAccounts.unregisterDevice(userAccount, registrationId);
	
	}
	
	
}
