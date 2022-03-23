package dev.db.livrariaio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.db.livrariaio.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
