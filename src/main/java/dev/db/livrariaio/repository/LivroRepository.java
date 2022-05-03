package dev.db.livrariaio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.db.livrariaio.model.Livro;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Page<Livro> findByCategoriaNome(String categoria, Pageable pageable);

    @Query("SELECT L FROM Livro L WHERE L.titulo LIKE %?1% OR L.autor.nome LIKE %?1%")
    Page<Livro> findByTituloOrAutorNome(String procurar, Pageable pageable);

    @Query(value = "SELECT L FROM Livro L WHERE L.dataPublicacao BETWEEN ?1 AND ?2")
    List<Livro> findLancamentos(LocalDate dataInicial, LocalDate dataFinal);

    @Override
    Page<Livro> findAll(Pageable pageable);
}
