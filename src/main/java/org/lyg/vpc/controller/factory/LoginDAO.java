package org.lyg.vpc.controller.factory;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONObject;
import org.lyg.vpc.view.Usr;
import org.lyg.vpc.view.UsrFull;
import org.lyg.vpc.view.UsrToken;

public interface LoginDAO {
	Usr selectUsrByUId(Integer uId) throws IOException;
	UsrToken selectUsrTokenByUId( Integer uId) throws IOException;
	UsrFull selectUsrFullByUId(Integer uId);
	Usr selectUsrByUEmail(String uEmail) throws IOException;
	//update
	void updateUsrByUId( Integer uId,
			String uName,
			String uAge,
			String uSex,
			String uPhone,
			String uAddress,
			String uWeChat,
			String uClass,
			String uEmail,
			String uQq) throws IOException;

	void updateUsrTokenByUId(Integer uId,
			String uKey,
			String uPassword,
			long uTime) throws IOException;
	void insertUsr( String uName,
			String uAge,
			String uSex,
			String uPhone,
			String uAddress,
			String uWeChat,
			String uClass,
			String uEmail,
			String uQq);
	void insertUsroken( Integer uId,
			String uKey,
			String uPassword,
			long uTime);

	void insertRowByTablePath(String BaseName, String tableName, JSONObject jsobj) throws FileNotFoundException, IOException;
}