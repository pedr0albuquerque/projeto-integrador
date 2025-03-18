package com.senac.projetomvc.controller;


import com.senac.projetomvc.model.Produto;
import com.senac.projetomvc.service.ProdutoService;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    
    private static List<Produto> produtos = new ArrayList<>();
    private static Produto produto = new Produto();
    
    @GetMapping("/listarProdutos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "listarProdutos";  // Nome do arquivo HTML para exibição
    }
    
    @GetMapping("/adicionarProduto")
    public String adicionarProduto(Model model) {
        model.addAttribute("produto", produto);
        return "adicionarProduto";  // Página para adicionar um novo produto
    }

    @PostMapping("/salvarProduto")
    public String salvarProduto(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/listarProdutos";
    }
    
    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable("id") int id, Model model) {
        Produto produto = produtoService.getProdutoId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "editarProduto";
        }
        return "redirect:/listarProdutos";  // Redireciona se ID for inválido
    }

    @PostMapping("/atualizarProduto/{id}")
    public String atualizarProduto(@PathVariable("id") int id, @ModelAttribute Produto produto) {
        produto.setId(id);
        produtoService.salvar(produto);
        return "redirect:/listarProdutos";
    }
    
    @GetMapping("/deletarProduto/{id}")
    public String deletarProduto(@PathVariable("id") int id) {
        produtoService.deletar(id);
        return "redirect:/listarProdutos";
    }
}
