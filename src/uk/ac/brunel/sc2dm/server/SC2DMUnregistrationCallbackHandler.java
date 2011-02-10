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
 * Public interface defining a default method which will be called always the registration id arrive for a 
 * client. 
 * @author Sergio Moyano Serrano (smoyano@correo.ugr.es)
 * Created: 15:07 - 2/08/11
 */
public interface SC2DMUnregistrationCallbackHandler {
	/**
	 * Some task like saving the registrationId along with the email should be done. 
	 */
	public void unregisterDevice(final String email, final String registrationId);

}
