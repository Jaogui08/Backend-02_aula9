package com.projetologinjoaogui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projetologinjoaogui.entity.Produtos;
import com.projetologinjoaogui.repository.ProdutosRepository;

@Service
public class ProdutosService {
	private final ProdutosRepository produtosRepository;

	public ProdutosService(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}
	
	public List<Produtos> findAllProdutos() {
		return produtosRepository.findAll();
	}
	
	public Produtos findProdutosById(Long id) {
		Optional<Produtos> existProdutos = produtosRepository.findById(id);
		return existProdutos.orElse(null);
	}
	
	public Produtos saveProdutos(Produtos produtos) {
		return produtosRepository.save(produtos);
	}
	
	public Produtos updateProdutos(Produtos produtos, Long id) {
		Optional<Produtos> existProdutos = produtosRepository.findById(id);
		if (existProdutos.isPresent()) {
			Produtos updProdutos = existProdutos.get();
			BeanUtils.copyProperties(produtos, updProdutos, "id");
			return produtosRepository.save(updProdutos);
		}
		return null;
	}
	
	public boolean deleteProdutos(Long id) {
		Optional<Produtos> existProdutos = produtosRepository.findById(id);
		if (existProdutos.isPresent()) {
			produtosRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
