package com.cg.services;

import java.util.Map;

import com.cg.dto.ChangePassDto;
import com.cg.dto.LoginDto;
import com.cg.entities.Login;
import com.cg.exceptions.LoginException;

/*
 * @Author: Supriyo Das
 */
public interface ILoginService {

	public Login doLogin(String email,String password,String role) throws LoginException;

//	public String encryptString(String str);

//	public String decryptString(String str);

	public String encryptLogin(Login loginAcnt);

	public String generateToken(Login login);

	public Map<String, Login> getAuthMap();

	public boolean verifyLogin(String tokenId) throws LoginException;
	public Login changePassword(ChangePassDto changePassDto) throws LoginException;
}
