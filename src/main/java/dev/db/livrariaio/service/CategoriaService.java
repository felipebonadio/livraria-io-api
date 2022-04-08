package dev.db.livrariaio.service;

import java.util.List;
import java.util.Optional;
import dev.db.livrariaio.exception.DomainBusinessException;
import org.springframework.stereotype.Service;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.CategoriaMapper;
import dev.db.livrariaio.model.Categoria;
import dev.db.livrariaio.repository.CategoriaRepository;

import static java.util.Objects.requireNonNullElse;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO findCategoriaById(Long id) {
        Optional<CategoriaDTO> categoriaToFind = categoriaRepository.findById(id).map(CategoriaMapper::categoriaToDTO);
        if (categoriaToFind.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada.");
        } else {
            return categoriaToFind.get();
        }
    }

    public Categoria findCategoriaByNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public List<CategoriaDTO> findAllCategorias() {
        return categoriaRepository.findAll().stream().map(CategoriaMapper::categoriaToDTO)
                .toList();
    }

    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = CategoriaMapper.dtoToCategoria(categoriaDTO);
        boolean categoriaJaExiste = findCategoriaByNome(categoria.getNome()) != null;
        if (categoriaJaExiste) {
            throw new DomainBusinessException("Não é possível cadastrar duas categorias com o mesmo nome.");
        }
        categoria.setId(null);
        return CategoriaMapper.categoriaToDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaToFind = categoriaRepository.findById(categoriaDTO.getId());
        if (categoriaToFind.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada.");
        }
        Categoria categoriaToUpdate = categoriaToFind.get();
        categoriaToUpdate.setNome(requireNonNullElse(categoriaDTO.getNome(), categoriaToUpdate.getNome()));
        categoriaToUpdate
                .setDescricao(requireNonNullElse(categoriaDTO.getDescricao(), categoriaToUpdate.getDescricao()));
        return CategoriaMapper.categoriaToDTO(categoriaRepository.save(categoriaToUpdate));
    }

    public void deleteCategoria(Long categoriaId) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(categoriaId);
        if (categoria.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada.");
        }
        categoriaRepository.delete(categoria.get());
    }
}
