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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author Sergio Moyano Serrano
 * Created: 09:20 - 2/07/11
 */
public class SC2DMessage {
	public static final String MESSAGE_KEY_REGISTRATION_ID = "registration_id";
	public static final String MESSAGE_KEY_STATS = "data.stats";
	public static final String MESSAGE_KEY_PAYLOAD_KEY = "data.key";
	public static final String MESSAGE_KEY_PAYLOAD_STATUS = "data.status";
    public static final String MESSAGE_KEY_PAYLOAD_MESSAGE = "data.message";
    public static final String MESSAGE_TYPE = "data.type";
    public static final String MESSAGE_COLLAPSE_KEY = "collapse_key";
    public static final String MESSAGE_TYPE_PAYLOAD = "2";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String DEFAULT_COLLAPSE_KEY = "sc2dm-msg";
	
	private String message = null;
	private String startTime = null;
	private String key =  "-1";
	private String status = "-1";
	private static String encoding = null;
	private String collapseKey = null;
	private String registrationId = null;
	
	public SC2DMessage(String message, String registrationId){
		this.message = message;
		this.registrationId = registrationId;
		collapseKey = DEFAULT_COLLAPSE_KEY;
		encoding = UTF_8_ENCODING;
		key = "1";
		status = "2";
		Date date = new Date();
		startTime = Integer.toString((int) date.getTime());
	}
	
	public SC2DMessage(String message, String registrationId, String collapseKey){
		this.message = message;
		this.registrationId = registrationId;
		this.collapseKey = collapseKey;
		encoding = UTF_8_ENCODING;
		Date date = new Date();
		startTime = Integer.toString((int) date.getTime());
	}
	
	public SC2DMessage(String message, String registrationId, String collapseKey, String encoding){
		this.message = message;
		this.registrationId = registrationId;
		this.collapseKey = collapseKey;
		SC2DMessage.encoding = encoding;
		Date date = new Date();
		startTime = Integer.toString((int) date.getTime());
	}
	
	public void setKey(int key){
		this.key = Integer.toString(key);
	}
	
	public void setStatus(int status){
		this.status = Integer.toString(status);
	}
	
	public void setStartTime(long startTime){
		this.startTime = Integer.toString((int)startTime); 
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
    	addEncodedParameter(sb, MESSAGE_KEY_REGISTRATION_ID, registrationId);
    	addEncodedParameter(sb, MESSAGE_COLLAPSE_KEY, collapseKey);
    	addEncodedParameter(sb, MESSAGE_TYPE, MESSAGE_TYPE_PAYLOAD);
    	addEncodedParameter(sb, MESSAGE_KEY_STATS, startTime);
    	addEncodedParameter(sb, MESSAGE_KEY_PAYLOAD_KEY, key);
    	addEncodedParameter(sb, MESSAGE_KEY_PAYLOAD_STATUS, status);
    	addEncodedParameter(sb, MESSAGE_KEY_PAYLOAD_MESSAGE, message);
    	return sb.toString();
	}
	
	public static void addEncodedParameter(StringBuilder sb, String name, String value) 
	{
        if (sb.length() > 0) {
           sb.append("&");
        }
        try {
    	        sb.append(URLEncoder.encode(name, encoding));
    	        sb.append("=");
    	        sb.append(URLEncoder.encode(value, encoding));
    	} catch (UnsupportedEncodingException e) {
    		        // Exception handling
    	}
    }

}
