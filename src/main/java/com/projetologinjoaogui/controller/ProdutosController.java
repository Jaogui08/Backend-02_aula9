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

import com.projetologinjoaogui.entity.Produtos;
import com.projetologinjoaogui.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
	private final ProdutosService produtosService;

	public ProdutosController(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Produtos>> findAllProdutoss() {
		List<Produtos> produtos = produtosService.findAllProdutos();
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> findProdutosById(@PathVariable Long id) {
		Produtos produtos = produtosService.findProdutosById(id);
		if (produtos != null) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Produtos> saveProdutos(@RequestBody Produtos produtos) {
		Produtos saveProdutos = produtosService.saveProdutos(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProdutos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produtos> updateProdutos(@RequestBody Produtos produtos, @PathVariable Long id) {
		Produtos updProdutos = produtosService.updateProdutos(produtos, id);
		if (updProdutos != null) {
			return ResponseEntity.ok(updProdutos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produtos> deleteProdutos(@PathVariable Long id) {
		boolean delProdutos = produtosService.deleteProdutos(id);
		if (delProdutos) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
