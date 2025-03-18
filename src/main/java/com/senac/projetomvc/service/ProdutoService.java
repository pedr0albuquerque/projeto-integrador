package com.senac.projetomvc.service;

import com.senac.projetomvc.model.Produto;
import com.senac.projetomvc.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    public Produto getProdutoId(Integer produtoId) {
        return produtoRepository.findById(produtoId).orElse(null); 
    }
    
    public Produto atualizarFilme(Integer produtoId, Produto produtoRequest) { 

        Produto produto = getProdutoId(produtoId); 

        produto.setNome(produtoRequest.getNome()); 
        produto.setQuantidade(produtoRequest.getQuantidade()); 
        produto.setPreco(produtoRequest.getPreco());
        produtoRepository.save(produto); 
        
        return produto; 
    }
    
    public void deletar(Integer id) {
        produtoRepository.deleteById(id);
    }
}
