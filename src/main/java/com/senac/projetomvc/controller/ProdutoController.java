package com.senac.projetomvc.controller;

import com.senac.projetomvc.controller.model.Produto;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<>();
    private static Produto produto = new Produto();

    @GetMapping("/listarProdutos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtos);
        return "listarProdutos";  // Nome do arquivo HTML para exibição
    }
    
    @GetMapping("/adicionarProduto")
    public String adicionarProduto(Model model) {
        model.addAttribute("produto", produto);
        return "adicionarProduto";  // Página para adicionar um novo produto
    }

    @PostMapping("/salvarProduto")
    public String salvarProduto(@ModelAttribute Produto produto) {
        produtos.add(produto);
        return "redirect:/listarProdutos";
    }
    
    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable("id") int id, Model model) {
        if (id >= 0 && id < produtos.size()) {  // Verifica se o índice existe na lista
            Produto produto = produtos.get(id);
            model.addAttribute("produto", produto);
            model.addAttribute("id", id);
            return "editarProduto";  // Página para edição
        }
        return "redirect:/listarProdutos";  // Redireciona se ID for inválido
    }

    @PostMapping("/atualizarProduto/{id}")
    public String atualizarProduto(@PathVariable("id") int id, @ModelAttribute Produto produto) {
        if (id >= 0 && id < produtos.size()) {  // Garante que o índice existe
            produtos.set(id, produto);  // Atualiza o produto na lista
        }
        return "redirect:/listarProdutos";
    }
    
    @GetMapping("/deletarProduto/{id}")
    public String deletarProduto(@PathVariable("id") int id) {
        if (id >= 0 && id < produtos.size()) {  // Verifica se o índice é válido
            produtos.remove(id);
        }
        return "redirect:/listarProdutos";
    }
}
