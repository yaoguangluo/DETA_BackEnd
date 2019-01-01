package org.lyg.vpc.process.portImpl;
import lombok.extern.slf4j.Slf4j;
import org.lyg.vpc.controller.port.RestLoginPort;
import org.lyg.vpc.transaction.TransactionDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("unused")
@RestController
@Slf4j
public class RestLoginPortImpl implements RestLoginPort {
	@Autowired
	private TransactionDelegate transactionDelegate;
	
	@Override
	public Map<String, Object> login(String uEmail, String uPassword) throws Exception {
		Map<String, Object> map = transactionDelegate.transactionLogin(uEmail, uPassword);
		return map;
	}

	@Override
	public Map<String, Object> logout(String uEmail, String uToken) throws IOException {
		Map<String, Object> output = new HashMap<String, Object>();
		output.put("userEmail", "friend");
		output.put("userToken", "123456");
		return output;
	}

	@Override
	public Map<String, Object> register(String uEmail, String uEmailEnsure, String uName, String uPassword,
			String uPassWDEnsure, String uAddress, String uPhone, String uWeChat,
			String uQq, String uAge, String uSex) throws Exception {
		Map<String, Object> output = transactionDelegate.transactionRegister(uEmail, uEmailEnsure, uName, uPassword,
				uPassWDEnsure, uAddress, uPhone, uWeChat,
				uQq, uAge, uSex);
		return output;
	}

	@Override
	public Map<String, Object> change(String uEmail, String uChange, String uChangeEnsure, String uToken,
			String uPassword) throws IOException {
		return null;
	}

	@Override
	public Map<String, Object> find(String uEmail) throws IOException {
		return null;
	}
}