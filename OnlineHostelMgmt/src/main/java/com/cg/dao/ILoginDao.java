package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Hostel;
import com.cg.entities.Login;


/*
 * @Author: Supriyo Das
 */
@Repository
public interface ILoginDao extends JpaRepository<Login, Integer> {
	public List<Login> findByEmail(String email);
}
