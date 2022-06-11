package dev.db.livrariaio.service;

import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Endereco;
import dev.db.livrariaio.repository.EnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static dev.db.livrariaio.LivrariaFactory.criarEndereco;
import static dev.db.livrariaio.LivrariaFactory.criarPessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoUnitTest {
    @Mock
    EnderecoRepository enderecoRepository;

    @InjectMocks
    EnderecoService enderecoService;

    @Test
    @DisplayName("Deve retornar um endereco por ID")
    void deveRetornarUmEnderecoPorId() {
        Endereco endereco = criarEndereco();
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(criarEndereco()));
        assertEquals(endereco, enderecoService.findEnderecoById(1L));
    }


    @Test
    @DisplayName("Deve retornar um erro ao não achar um endereco por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharEnderecoPorId() {
        when(enderecoRepository.findById(2L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> enderecoService.findEnderecoById(2L));
    }

    @Test
    @DisplayName("Deve retornar uma lista de enderecos")
    void deveRetornarUmaListaDePessoa() {
        List<Endereco> enderecos = List.of(criarEndereco());
        when(enderecoRepository.findAll()).thenReturn(enderecos);
        assertEquals(enderecos, enderecoService.findAllEndereco());
    }

    @Test
    @DisplayName("Deve criar um endereco")
    void deveCriarUmendereco() {
        when(enderecoRepository.save(any())).thenReturn(criarEndereco());
        assertEquals(criarEndereco(), enderecoService.saveEndereco(criarEndereco()));
    }

    @Test
    @DisplayName("Deve atualizar um endereco")
    void deveAtualizarUmEndereco() {
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(criarEndereco()));
        when(enderecoRepository.save(criarEndereco())).thenReturn(criarEndereco());
        assertEquals(criarEndereco(), enderecoService.updateEndereco(criarEndereco()));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar um endereco e não encontra-la")
    void deveRetornarNotFoundAoProcurarEnderecoAtualizarENaoEncontrar() {
        when(enderecoRepository.findById(1L)).thenThrow(NotFoundException.class);
        Endereco atualizar = criarEndereco();
        assertThrows(NotFoundException.class, () -> enderecoService.updateEndereco(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar um endereco nulo")
    void deveRetornarExceptionalAoCriarEndereco() {
        when(enderecoRepository.save(any())).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> enderecoService.saveEndereco(criarEndereco()));
    }

    @Test
    @DisplayName("Deve apagar um endereco")
    void deveApagarUmEndereco() {
        criarPessoa().setId(1L);
        when(enderecoRepository.findById(any())).thenReturn(Optional.of(criarEndereco()));
        enderecoService.deleteEndereco(1L);
        verify(enderecoRepository, times(1)).delete(criarEndereco());
    }
}
