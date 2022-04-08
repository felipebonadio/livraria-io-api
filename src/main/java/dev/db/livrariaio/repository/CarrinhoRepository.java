package dev.db.livrariaio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.db.livrariaio.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}
