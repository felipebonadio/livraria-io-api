package dev.db.livrariaio.service;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.AutorMapper;
import dev.db.livrariaio.model.Autor;
import dev.db.livrariaio.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorDTO findAutorById(Long id) {
        Optional<AutorDTO> autorToFind = autorRepository.findById(id).map(AutorMapper::autorToDTO);
        if (autorToFind.isEmpty()) {
            throw new NotFoundException("Autor não encontrado.");
        }
        return autorToFind.get();
    }

    public List<AutorDTO> findAllAutores() {
        return autorRepository.findAll().stream().map(AutorMapper::autorToDTO)
                .toList();
    }

    public AutorDTO saveAutor(AutorDTO autorDTO) {
        Autor autor = AutorMapper.dtoToAutor(autorDTO);
        autor.setId(null);
        return AutorMapper.autorToDTO(autorRepository.save(autor));
    }

    public AutorDTO updateAutor(AutorDTO autorDTO) {
        Optional<Autor> autorToFind = autorRepository.findById(autorDTO.getId());
        if (autorToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o autor com o ID: " + autorDTO.getId() + ", pois ele não existe.");
        }
        Autor autorToUpdate = autorToFind.get();
        autorToUpdate.setNome(requireNonNullElse(autorDTO.getNome(), autorToUpdate.getNome()));
        autorToUpdate.setEmail(requireNonNullElse(autorDTO.getEmail(), autorToUpdate.getEmail()));
        autorToUpdate.setDescricao(requireNonNullElse(autorDTO.getDescricao(), autorToUpdate.getDescricao()));
        autorToUpdate.setDataCriacao(requireNonNullElse(autorDTO.getDataCriacao(), autorToUpdate.getDataCriacao()));
        return AutorMapper.autorToDTO(autorRepository.save(autorToUpdate));
    }

    public void deleteAutor(Long autorId) {
        Optional<Autor> autor = this.autorRepository.findById(autorId);
        if (autor.isEmpty()) {
            throw new NotFoundException("Autor não encontrado.");
        }
        autorRepository.delete(autor.get());
    }
}
