package com.projetologinjoaogui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetologinjoaogui.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
	Login findByUsername(String username);
}
