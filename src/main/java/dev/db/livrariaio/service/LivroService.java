package dev.db.livrariaio.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.AutorMapper;
import dev.db.livrariaio.mapper.CategoriaMapper;
import dev.db.livrariaio.mapper.LivroMapper;
import dev.db.livrariaio.model.Livro;
import dev.db.livrariaio.repository.LivroRepository;

import static java.util.Objects.requireNonNullElse;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroDTO findLivroById(Long id) {
        Optional<LivroDTO> livroToFind = livroRepository.findById(id).map(LivroMapper::livroToDTO);
        if (livroToFind.isEmpty()) {
            throw new NotFoundException("Livro não encontrado.");
        }
        return livroToFind.get();
    }

    public Page<LivroDTO> findLivroByCategoria(String categoria, Pageable pageable) {
        return livroRepository.findByCategoriaNome(categoria, pageable).map(LivroMapper::livroToDTO);
    }

    public Page<LivroDTO> findByTituloOrAutorNome(String procurar, Pageable pageable) {
        return this.livroRepository.findByTituloOrAutorNome(procurar, pageable).map(LivroMapper::livroToDTO);
    }

    public Page<LivroDTO> findAllLivros(Pageable pageable) {
        return livroRepository.findAll(pageable).map(LivroMapper::livroToDTO);
    }

    public LivroDTO saveLivro(LivroDTO livroDTO) {
        Livro livro = LivroMapper.dtoToLivro(livroDTO);
        livro.setId(null);
        return LivroMapper.livroToDTO(livroRepository.save(livro));
    }

    public LivroDTO updateLivro(LivroDTO livroDTO) {
        Optional<Livro> livroToFind = livroRepository.findById(livroDTO.getId());
        if (livroToFind.isEmpty()) {
            throw new NotFoundException("Livro não encontrado para atualizar.");
        }
        Livro livroToUpdate = livroToFind.get();
        livroToUpdate.setTitulo(requireNonNullElse(livroDTO.getTitulo(), livroToUpdate.getTitulo()));
        livroToUpdate.setSumario(requireNonNullElse(livroDTO.getSumario(), livroToUpdate.getSumario()));
        livroToUpdate.setPreco(requireNonNullElse(livroDTO.getPreco(), livroToUpdate.getPreco()));
        livroToUpdate
                .setDataPublicacao(requireNonNullElse(livroDTO.getDataPublicacao(), livroToUpdate.getDataPublicacao()));
        livroToUpdate.setCapa(requireNonNullElse(livroDTO.getCapa(), livroToUpdate.getCapa()));
        livroToUpdate.setIsbn(requireNonNullElse(livroDTO.getIsbn(), livroToUpdate.getIsbn()));
        livroToUpdate
                .setNumeroPaginas(requireNonNullElse(livroDTO.getNumeroPaginas(), livroToUpdate.getNumeroPaginas()));
        livroToUpdate
                .setAutor(requireNonNullElse(AutorMapper.dtoToAutor(livroDTO.getAutorDTO()), livroToUpdate.getAutor()));
        livroToUpdate.setCategoria(requireNonNullElse(CategoriaMapper.dtoToCategoria(livroDTO.getCategoriaDTO()),
                livroToUpdate.getCategoria()));
        return LivroMapper.livroToDTO(livroRepository.save(livroToUpdate));
    }

    public void deleteLivro(Long livroId) {
        Optional<Livro> deletedLivro = livroRepository.findById(livroId);
        if (deletedLivro.isEmpty()) {
            throw new NotFoundException("Não foi possível excluir livro.");
        }
        livroRepository.delete(deletedLivro.get());
    }
}