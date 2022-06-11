package dev.db.livrariaio.service;



import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Pessoa;
import dev.db.livrariaio.repository.PessoaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import static dev.db.livrariaio.LivrariaFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaUnitTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    PessoaService pessoaService;

    @Test
    @DisplayName("Deve retornar uma pessoa por ID")
    void deveRetornarUmaPessoaPorId() {
        Pessoa pessoa = criarPessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(criarPessoa()));
        assertEquals(pessoa, pessoaService.findPessoaById(1L));
    }


    @Test
    @DisplayName("Deve retornar um erro ao não achar uma pessoa por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharPessoaPorId() {
        when(pessoaRepository.findById(2L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> pessoaService.findPessoaById(2L));
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void deveRetornarUmaListaDePessoa() {
        List<Pessoa> pessoas = List.of(criarPessoa());
        when(pessoaRepository.findAll()).thenReturn(pessoas);
        assertEquals(pessoas, pessoaService.findAllPessoas());
    }

    @Test
    @DisplayName("Deve criar uma pessoa")
    void deveCriarUmaPessoa() {
        when(pessoaRepository.save(any())).thenReturn(criarPessoa());
        assertEquals(criarPessoa(), pessoaService.savePessoa(criarPessoa()));
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void deveAtualizarUmaPessoa() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(criarPessoa()));
        when(pessoaRepository.save(criarPessoa())).thenReturn(criarPessoa());
        assertEquals(criarPessoa(), pessoaService.updatePessoa(criarPessoa()));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar uma pessoa e não encontra-la")
    void deveRetornarNotFoundAoProcurarPessoaAtualizarENaoEncontrar() {
        when(pessoaRepository.findById(1L)).thenThrow(NotFoundException.class);
        Pessoa atualizar = criarPessoa();
        assertThrows(NotFoundException.class, () -> pessoaService.updatePessoa(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar uma pessoa nulo")
    void deveRetornarExceptionalAoCriarPessoa() {
        when(pessoaRepository.save(any())).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> pessoaService.savePessoa(criarPessoa()));
    }

    @Test
    @DisplayName("Deve apagar uma pessoa")
    void deveApagarUmaPessoa() {
        criarPessoa().setId(1L);
        when(pessoaRepository.findById(any())).thenReturn(Optional.of(criarPessoa()));
        pessoaService.deletePessoa(1L);
        verify(pessoaRepository, times(1)).delete(criarPessoa());
    }
}
