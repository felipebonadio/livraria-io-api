package dev.db.livrariaio.service;

import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Endereco;
import dev.db.livrariaio.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import static java.util.Objects.requireNonNullElse;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco findEnderecoById(Long id){
        Optional<Endereco> enderecoToFind = enderecoRepository.findById(id);
        if(enderecoToFind.isEmpty()){
            throw new NotFoundException("Endereço não encontrado!");
        }
        return enderecoToFind.get();
    }

    public List<Endereco> findAllEndereco(){
        return enderecoRepository.findAll();
    }

    public Endereco saveEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Endereco endereco){
        Optional<Endereco> enderecoToFind = enderecoRepository.findById(endereco.getId());
        if(enderecoToFind.isEmpty()){
            throw new NotFoundException("Endereço não encontrado");
        }
        Endereco enderecoToUpdate = enderecoToFind.get();
        enderecoToUpdate.setLogradouro(requireNonNullElse(endereco.getLogradouro(), enderecoToUpdate.getLogradouro()));
        enderecoToUpdate.setLocalidade(requireNonNullElse(endereco.getLocalidade(), enderecoToUpdate.getLocalidade()));
        enderecoToUpdate.setBairro(requireNonNullElse(endereco.getBairro(), enderecoToUpdate.getBairro()));
        enderecoToUpdate.setUf(requireNonNullElse(endereco.getUf(), enderecoToUpdate.getUf()));
        enderecoToUpdate.setCep(requireNonNullElse(endereco.getCep(), enderecoToUpdate.getCep()));
        enderecoToUpdate.setComplemento(requireNonNullElse(endereco.getComplemento(), enderecoToUpdate.getComplemento()));
        return enderecoRepository.save(enderecoToUpdate);
    }

    public void deleteEndereco(Long enderecoId){
        Optional<Endereco> enderecoToDelete = enderecoRepository.findById(enderecoId);
        if(enderecoToDelete.isEmpty()){
            throw new NotFoundException("Endereço não encontrado!");
        }
        enderecoRepository.delete(enderecoToDelete.get());
    }
}
