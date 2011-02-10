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
 * @author Sergio Moyano Serrano
 * Created: 13:17 - 2/08/11
 */

public class SC2DMRegister extends AbstractSC2DMRegister{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4928309242783352581L;
	private SC2DMRegistrationCallbackHandler callbackHandler;
	public SC2DMRegister(){
		callbackHandler = new SC2DMDefaultRegistrationCallbackHandler();
		super.setCallbackHandler(callbackHandler);
	}
}
