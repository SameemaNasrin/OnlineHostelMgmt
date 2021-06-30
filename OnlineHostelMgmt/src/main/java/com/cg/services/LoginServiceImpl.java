package com.cg.services;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ILoginDao;
import com.cg.dto.ChangePassDto;
import com.cg.dto.LoginDto;
import com.cg.entities.Login;
import com.cg.exceptions.LoginException;
import com.cg.helper.LoginConstants;

/*
 * @Author: Supriyo Das
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao logindao;

	Map<String, Login> authMap = new HashMap<>();

	@Override
	public Login doLogin(String email, String password, String role) throws LoginException {
		Login login = null;

		List<Login> loginList = logindao.findByEmail(email);
		if (loginList.isEmpty()) {
			throw new LoginException("No user found with email " + email);
		} else {
			login = loginList.get(0);
			if (!login.getRole().equalsIgnoreCase(role)) {
				throw new LoginException(email + " is not registered as " + role);
			} else {
				if (!login.getPassword().contentEquals(encryptPassword(password))) {
					throw new LoginException(LoginConstants.PASSWORD_WRONG);
				}
				return login;
			}
		}

	}

	@Override
	public String generateToken(Login login) {
		String token = encryptLogin(login);
		authMap.put(token, login);
		return token;
	}

	public Map<String, Login> getAuthMap() {
		return this.authMap;
	}

	public static String encryptPassword(String password) {
		byte[] plainText = password.getBytes();
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.reset();
			md.update(plainText);
			byte[] encodedPassword = md.digest();

			for (int i = 0; i < encodedPassword.length; i++) {
				if ((encodedPassword[i] & 0xff) < 0x10) {
					sb.append("0");
				}

				sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String encryptLogin(Login loginAcnt) {
		SecureRandom secureRandom = new SecureRandom();
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		String str = base64Encoder.encodeToString(randomBytes) + loginAcnt.getId()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return str;
//		return encryptString(loginAcnt.getEmpId().toString()) + "-" + encryptString(loginAcnt.getRole());
	}

	public boolean verifyLogin(String tokenId) throws LoginException {
		if (!authMap.containsKey(tokenId)) {
			throw new LoginException(LoginConstants.INVALID_LOGIN_TOKEN);
		}
		return true;
	}

	@Override
	public Login changePassword(ChangePassDto changePassDto) throws LoginException {
		if(!changePassDto.getNewPass().equals(changePassDto.getCnf_newPass())) {
			throw new LoginException("New password and confirm password didn't match");
		}
		Login login = null;

		List<Login> loginList = logindao.findByEmail(changePassDto.getEmail());
		if (loginList.isEmpty()) {
			throw new LoginException("No user found with email " + changePassDto.getEmail());
		} else {
			login = loginList.get(0);
			if (!login.getPassword().contentEquals(encryptPassword(changePassDto.getOldPass()))) {
				throw new LoginException(LoginConstants.PASSWORD_WRONG);
			}
			//all set to go
			login.setPassword(encryptPassword(changePassDto.getNewPass()));
			return logindao.save(login);
		}
	}

}
