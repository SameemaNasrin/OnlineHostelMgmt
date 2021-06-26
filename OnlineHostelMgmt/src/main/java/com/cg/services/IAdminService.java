package com.cg.services;

import java.util.Map;

import com.cg.dto.AdminDto;
import com.cg.exceptions.EmailAlreadyExistException;

public interface IAdminService {
	public Map<String, String> addAdmin(AdminDto adminDto) throws EmailAlreadyExistException;
}
