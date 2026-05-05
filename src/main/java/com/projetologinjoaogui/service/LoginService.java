package com.projetologinjoaogui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projetologinjoaogui.entity.Login;
import com.projetologinjoaogui.repository.LoginRepository;

@Service
public class LoginService {
	private final LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	public List<Login> findAllLogins() {
		return loginRepository.findAll();
	}
	
	public Login findLoginById(Long id) {
		Optional<Login> existLogin = loginRepository.findById(id);
		return existLogin.orElse(null);
	}
	
	public Login saveLogin(Login login) {
		return loginRepository.save(login);
	}
	
	public Login updateLogin(Login login, Long id) {
		Optional<Login> existLogin = loginRepository.findById(id);
		if (existLogin.isPresent()) {
			Login updLogin = existLogin.get();
			BeanUtils.copyProperties(login, updLogin, "id");
			return loginRepository.save(updLogin);
		}
		return null;
	}
	
	public boolean deleteLogin(Long id) {
		Optional<Login> existLogin = loginRepository.findById(id);
		if (existLogin.isPresent()) {
			loginRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Login authenticate(String username, String password) {
		Login user = loginRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
