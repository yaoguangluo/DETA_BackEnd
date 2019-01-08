package org.deta.boot.rest;
import java.util.Map;
import org.lyg.common.maps.VtoV;
//import org.lyg.vpc.controller.port.RestLoginPort;
import org.lyg.vpc.process.portImpl.RestLoginPortImpl;
public class VPC {
//	public static RestLoginPort restLoginPort;
	public static String forward(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/login")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.login(data.get("uEmail"),data.get("uPassword")));	
		}
//		if(string.equalsIgnoreCase("/get")){
//			return new VtoV().ObjectToJsonString(new RestCachePortImpl().get(data.get("key"), data.get("email")
//					,  data.get("password")));	
//		}
//		if(string.equalsIgnoreCase("/put")){
//			return new VtoV().ObjectToJsonString(new RestCachePortImpl().put(data.get("key"), data.get("value")
//					,data.get("time")  , data.get("email"), data.get("password")));	
//		}
		return "";
	}
//	public static void initController() throws Exception {
//		restLoginPort = new RestLoginPortImpl();
//	}
}