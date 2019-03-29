package org.deta.boot.rest;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.vpc.process.portImpl.RestLoginPortImpl;
import org.lyg.vpc.process.portImpl.RestNLPPortImpl;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.engine.analysis.Analyzer;
public class VPC {
//	public static RestLoginPort restLoginPort;
	public static String forward(EmotionMap emotionMap, Analyzer analyzer, String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/login")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.login(data.get("uEmail"),data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/find")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.find(data.get("uEmail")));
		}
		if(string.equalsIgnoreCase("/logout")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.logout(data.get("uEmail"), data.get("uToken")));
		}
		if(string.equalsIgnoreCase("/register")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.register(data.get("uEmail"), data.get("uEmailEnsure")
					, data.get("uName"), data.get("uPassword"), data.get("uPassWDEnsure"), data.get("uAddress")
					, data.get("uPhone"), data.get("uWeChat"), data.get("uQq"), data.get("uAge"), data.get("uSex")));	
		}
		if(string.equalsIgnoreCase("/change")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.change(data.get("uEmail"), data.get("uChange")
					, data.get("uChangeEnsure"),data.get("uToken"), data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/checkStatus")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.checkStatus(data.get("token")));	
		}
		if(string.equalsIgnoreCase("/dataWS")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataWS(analyzer, data.get("input")));	
		}
		if(string.equalsIgnoreCase("/dataCX")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataCX(analyzer, data.get("input")));	
		}
		if(string.equalsIgnoreCase("/dataCY")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataCY(analyzer, data.get("input")));	
		}
		if(string.equalsIgnoreCase("/dataCG")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataCG(analyzer, data.get("input")));	
		}
		if(string.equalsIgnoreCase("/dataCJ")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataCJ(emotionMap, analyzer, data.get("input")));	
		}
		if(string.equalsIgnoreCase("/dataCL")){
			return new VtoV().ObjectToJsonString(RestNLPPortImpl.dataCL(analyzer, data.get("input")));	
		}
		return "";
	}
}