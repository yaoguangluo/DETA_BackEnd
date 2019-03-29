package org.lyg.vpc.transaction;
import org.json.JSONObject;
import org.lyg.common.maps.VtoV;
import org.lyg.common.utils.DetaDBUtil;
import org.lyg.common.utils.TokenUtil;
import org.lyg.vpc.process.companyImpl.LoginServiceImpl;
import org.lyg.vpc.view.Usr;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionDelegate {
//	@Autowired
//	private LoginService loginService;
	@SuppressWarnings("deprecation")
	public static Map<String, Object> transactionLogin(String uEmail, String uPassword)throws Exception {
		String response = DetaDBUtil.DBRequest("/login?uEmail=" + URLEncoder.encode(uEmail) + "&uPassword=" 
				+ URLEncoder.encode(uPassword));
		Map<String, Object> out = new VtoV().JsonObjectToMap(new JSONObject(response));

		//		Usr usr = loginService.findUsrByUEmail(uEmail);
		//		UsrToken usrToken = loginService.findUsrTokenByUId(usr.getuId());
		//		String password = TokenUtil.getSecondMD5Password(uPassword);
		//		if (!password.equals(usrToken.getuPassword())) {
		//			Map<String, Object> out = new HashMap<>();
		//			out.put("loginInfo", "unsuccess");
		//			out.put("returnResult", "密码不正确");
		//			return out;
		//		}
		//		Token token = TokenUtil.getNewTokenFromUsrAndUsrToken(usr, usrToken);
		//		String json = new Gson().toJson(token);
		//		String jsonToken = StringUtil.encode(json);
		//		loginService.updateUsrTokenByUId(usr.getuId(), token.getuKey(), password, token.getuTime()/1000);
		//		Map<String, Object> out = new HashMap<>();
		//		out.put("userToken", jsonToken);
		//		out.put("userEmail", uEmail);
		//		out.put("loginInfo", "success");
		return out;
	}

	public static Map<String, Object> transactionRegister(String uEmail, String uEmailEnsure, String uName, String uPassword,
			String uPassWDEnsure, String uAddress, String uPhone, String uWeChat, String uQq, String uAge,
			String uSex) throws Exception {
		Usr usr = LoginServiceImpl.findUsrByUEmail(uEmail);
		if(usr.getuEmail()!=null) {
			Map<String, Object> out = new HashMap<>();
			out.put("loginInfo", "unsuccess");
			out.put("returnResult", "邮箱已注册");
			return out;
		}
		JSONObject jsobj=new JSONObject();
		jsobj.put("u_email", uEmail);
		jsobj.put("u_name", uName);
		jsobj.put("u_password", TokenUtil.getSecondMD5Password(uPassword));
		jsobj.put("u_address", uAddress);
		jsobj.put("u_phone", uPhone);
		jsobj.put("u_weChat", uWeChat);
		jsobj.put("u_qq", uQq);
		jsobj.put("u_age", uAge);
		jsobj.put("u_sex", uSex);
		jsobj.put("u_id", "random");
		LoginServiceImpl.insertRowByTablePath("backend", "usr", jsobj);
		usr = LoginServiceImpl.findUsrByUEmail(uEmail);
		JSONObject jsobjToken = new JSONObject();
		jsobjToken.put("u_id", usr.getuId());
		jsobjToken.put("u_level", "low");
		jsobjToken.put("u_password", TokenUtil.getSecondMD5Password(uPassword));
		LoginServiceImpl.insertRowByTablePath("backend", "usrToken", jsobjToken);
		return transactionLogin(uEmail, uPassword);
	}
}