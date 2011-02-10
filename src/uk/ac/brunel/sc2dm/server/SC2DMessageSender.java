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

/**
 * @author Sergio Moyano Serrano
 * Created: 10:00 - 2/06/11
 */

public class SC2DMessageSender {
	SC2DMConnection connection = null;
	
	public SC2DMessageSender(SC2DMConnection connection){
		this.connection = connection;
	}
		
	public void pushMessage(SC2DMessage message){
		try {					
			OutputStreamWriter writer;		
			writer = new OutputStreamWriter(connection.getHttpURLConnection().getOutputStream());
		  
        	String data = message.toString();
    
        	writer.write(data);
			writer.flush();
        	writer.close();
        	BufferedReader in = new BufferedReader(
        	new InputStreamReader(connection.getHttpURLConnection().getInputStream()));
    		String line;
    		while ((line = in.readLine()) != null) {
    			if(line.startsWith("Error"))
    				SC2DMAuthToken.authenticate();
    			System.out.println("Sender:"+line);
    		}	
    		in.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
