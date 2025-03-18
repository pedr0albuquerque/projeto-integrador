
package com.senac.projetomvc.repository;

import com.senac.projetomvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
