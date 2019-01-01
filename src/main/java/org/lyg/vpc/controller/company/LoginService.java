package org.lyg.vpc.controller.company;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.lyg.vpc.view.Usr;
import org.lyg.vpc.view.UsrToken;
public interface LoginService {
	Usr findUsrByUEmail(String uEmail) throws IOException;
	UsrToken findUsrTokenByUId(Integer uId) throws IOException;
	void updateUsrTokenByUId(Integer uId, String key, String uPassword, long uTime) throws IOException;
	void insertRowByTablePath(String BaseName, String TableName, JSONObject jsobj) throws FileNotFoundException, IOException;
	String checkStatus(String token, String level) throws NumberFormatException, JSONException, IOException, Exception;
}
