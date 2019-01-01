package org.lyg.vpc.controller.port;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.lyg.common.RequestLimit;
import java.io.IOException;
import java.util.Map;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_ADDRESS;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_AGE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_CHANGE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_CHANGE_ENSURE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_EMAIL;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_EMAIL_ENSURE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_NAME;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_PHONE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_PSTOKEN;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_PSWD;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_PSWD_ENSURE;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_QQ;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_SEX;
import static org.lyg.common.constants.DetaDBConstant.DB_LOGIN_U_WECHAT;
import static org.lyg.common.constants.DetaDBConstant.DIGIT_60000;
import static org.lyg.common.constants.DetaDBConstant.DIGIT_ONE;
import static org.lyg.common.constants.DetaDBConstant.REST_JSON_CONFIG;
import static org.lyg.common.constants.DetaDBConstant.REST_LOGIN_CHANGE;
import static org.lyg.common.constants.DetaDBConstant.REST_LOGIN_FIND;
import static org.lyg.common.constants.DetaDBConstant.REST_LOGIN_LOGIN;
import static org.lyg.common.constants.DetaDBConstant.REST_LOGIN_LOGOUT;
import static org.lyg.common.constants.DetaDBConstant.REST_LOGIN_REGISTER;
@RestController
public interface RestLoginPort {
	@RequestLimit(count = DIGIT_ONE, time = DIGIT_60000)
	@POST
	@RequestMapping(REST_LOGIN_LOGIN)
	@Produces(REST_JSON_CONFIG)
	Map<String, Object> login(@QueryParam(DB_LOGIN_U_EMAIL) String uEmail,
			@QueryParam(DB_LOGIN_U_PSWD) String uPassword) throws Exception;

	@RequestLimit(count = DIGIT_ONE, time = DIGIT_60000)
	@GET
	@RequestMapping(REST_LOGIN_LOGOUT)
	@Produces(REST_JSON_CONFIG)
	Map<String, Object> logout(@QueryParam(DB_LOGIN_U_EMAIL) String uEmail,
			@QueryParam(DB_LOGIN_U_PSTOKEN) String uToken) throws IOException;

	@RequestLimit(count = DIGIT_ONE, time = DIGIT_60000)
	@POST
	@RequestMapping(REST_LOGIN_REGISTER)
	@Produces(REST_JSON_CONFIG)
	Map<String, Object> register(@QueryParam(DB_LOGIN_U_EMAIL) String uEmail,
			@QueryParam(DB_LOGIN_U_EMAIL_ENSURE) String uEmailEnsure,
			@QueryParam(DB_LOGIN_U_NAME) String uName,
			@QueryParam(DB_LOGIN_U_PSWD) String uPassword,
			@QueryParam(DB_LOGIN_U_PSWD_ENSURE) String uPassWDEnsure,
			@QueryParam(DB_LOGIN_U_ADDRESS) String uAddress,
			@QueryParam(DB_LOGIN_U_PHONE) String uPhone,
			@QueryParam(DB_LOGIN_U_WECHAT) String uWeChat,
			@QueryParam(DB_LOGIN_U_QQ) String uQq,
			@QueryParam(DB_LOGIN_U_AGE) String uAge,
			@QueryParam(DB_LOGIN_U_SEX) String uSex) throws IOException, Exception;

	@RequestLimit(count = DIGIT_ONE, time = DIGIT_60000)
	@POST
	@RequestMapping(REST_LOGIN_CHANGE)
	@Produces(REST_JSON_CONFIG)
	Map<String, Object> change(@QueryParam(DB_LOGIN_U_EMAIL) String uEmail,
			@QueryParam(DB_LOGIN_U_CHANGE) String uChange,
			@QueryParam(DB_LOGIN_U_CHANGE_ENSURE) String uChangeEnsure,
			@QueryParam(DB_LOGIN_U_PSTOKEN) String uToken,
			@QueryParam(DB_LOGIN_U_PSWD) String uPassword) throws IOException;

	@RequestLimit(count = DIGIT_ONE, time = DIGIT_60000)
	@POST
	@RequestMapping(REST_LOGIN_FIND)
	@Produces(REST_JSON_CONFIG)
	Map<String, Object> find(@QueryParam(DB_LOGIN_U_EMAIL) String uEmail) throws IOException;
}