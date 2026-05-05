package com.projetologinjoaogui.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetologinjoaogui.entity.Login;
import com.projetologinjoaogui.service.LoginService;

@RestController
@RequestMapping("/users")
public class LoginController {
	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Login>> findAllLogins() {
		List<Login> login = loginService.findAllLogins();
		return ResponseEntity.ok(login);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Login> findLoginById(@PathVariable Long id) {
		Login login = loginService.findLoginById(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Login> saveLogin(@RequestBody Login login) {
		Login saveLogin = loginService.saveLogin(login);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveLogin);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Login> updateLogin(@RequestBody Login login, @PathVariable Long id) {
		Login updLogin = loginService.updateLogin(login, id);
		if (updLogin != null) {
			return ResponseEntity.ok(updLogin);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Login> deleteLogin(@PathVariable Long id) {
		boolean delLogin = loginService.deleteLogin(id);
		if (delLogin) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Login> authenticate(@RequestBody Login loginDetails) {
		Login authenticatedUser = loginService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
		if (authenticatedUser != null) {
			authenticatedUser.setPassword(null);
			return ResponseEntity.ok(authenticatedUser);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
