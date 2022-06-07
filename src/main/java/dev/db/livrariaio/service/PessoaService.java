package dev.db.livrariaio.service;

import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Pessoa;
import dev.db.livrariaio.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static java.util.Objects.requireNonNullElse;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa findPessoaById(Long id){
        Optional<Pessoa> pessoaToFind = this.pessoaRepository.findById(id);
        if(pessoaToFind.isEmpty()){
            throw new NotFoundException("Livro não encontrado.");
        }
        return pessoaToFind.get();
    }

    public List<Pessoa> findAllPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa savePessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Pessoa pessoa){
        Optional<Pessoa> pessoaToFind = this.pessoaRepository.findById(pessoa.getId());
        if(pessoaToFind.isEmpty()){
            throw new NotFoundException("Pessoa não encontrada!");
        }
        Pessoa pessoaToUpdate = pessoaToFind.get();
        pessoaToUpdate.setNome(requireNonNullElse(pessoa.getNome(), pessoaToUpdate.getNome()));
        pessoaToUpdate.setCpf(requireNonNullElse(pessoa.getCpf(), pessoaToUpdate.getCpf()));
        pessoaToUpdate.setEndereco(requireNonNullElse(pessoa.getEndereco(), pessoaToUpdate.getEndereco()));
        pessoaToUpdate.setCarrinho(requireNonNullElse(pessoa.getCarrinho(), pessoaToUpdate.getCarrinho()));
        return this.pessoaRepository.save(pessoaToUpdate);
    }

    public void deletePessoa(Long pessoaId){
        Optional<Pessoa> pessoaToDelete = pessoaRepository.findById(pessoaId);
        if(pessoaToDelete.isEmpty()){
            throw new NotFoundException("Pessoa não encontrada!");
        }
        pessoaRepository.delete(pessoaToDelete.get());
    }
}
