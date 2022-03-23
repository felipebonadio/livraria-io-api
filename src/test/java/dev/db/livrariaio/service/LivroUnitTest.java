package dev.db.livrariaio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.exception.BadRequestException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.LivroMapper;
import dev.db.livrariaio.model.Autor;
import dev.db.livrariaio.model.Categoria;
import dev.db.livrariaio.model.Livro;
import dev.db.livrariaio.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
public class LivroUnitTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    public Categoria criarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("categoria");
        categoria.setDescricao("descricao");
        return categoria;
    }

    public Autor criarAutor() {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setDataCriacao(LocalDate.now());
        autor.setNome("nome");
        autor.setEmail("email");
        autor.setDescricao("descricao");
        return autor;
    }

    public Livro criarLivro() {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("titulo");
        livro.setNumeroPaginas(50);
        livro.setIsbn("isbn");
        livro.setSumario("sumario");
        livro.setPreco(new BigDecimal("50.00"));
        livro.setCapa("capa");
        livro.setDataPublicacao(LocalDate.now());
        livro.setCategoria(criarCategoria());
        livro.setAutor(criarAutor());
        return livro;
    }

    public CategoriaDTO criarCategoriaDto() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1L);
        categoriaDTO.setNome("categoria");
        categoriaDTO.setDescricao("descricao");
        return categoriaDTO;
    }

    public AutorDTO criarAutorDto() {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(1L);
        autorDTO.setDataCriacao(LocalDate.now());
        autorDTO.setNome("nome");
        autorDTO.setEmail("email");
        autorDTO.setDescricao("descricao");
        return autorDTO;
    }

    public LivroDTO criarLivroDto() {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(1L);
        livroDTO.setTitulo("titulo");
        livroDTO.setNumeroPaginas(50);
        livroDTO.setIsbn("isbn");
        livroDTO.setSumario("sumario");
        livroDTO.setPreco(new BigDecimal("50.00"));
        livroDTO.setCapa("capa");
        livroDTO.setDataPublicacao(LocalDate.now());
        livroDTO.setCategoriaDTO(criarCategoriaDto());
        livroDTO.setAutorDTO(criarAutorDto());
        return livroDTO;
    }

    @Test
    @DisplayName("Deve retornar um livro por ID")
    void deveRetornarUmLivroPorId() {
        LivroDTO livroDTO = LivroMapper.livroToDTO(criarLivro());
        when(livroRepository.findById(1L)).thenReturn(Optional.ofNullable(criarLivro()));
        assertEquals(livroDTO, livroService.findLivroById(1L));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao não achar um livro por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharLivroPorId() {
        when(livroRepository.findById(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> livroService.findLivroById(any()));
    }

    @Test
    @DisplayName("Deve retornar uma lista de livros")
    void deveRetornarUmaListaDeLivros() {
        Pageable pageable = PageRequest.of(0, 1);
        List<LivroDTO> listaDto = List.of(criarLivroDto());
        List<Livro> listaLivroEntidade = listaDto.stream().map(LivroMapper::dtoToLivro).toList();
        Page<Livro> pageLivros = new PageImpl(listaLivroEntidade, pageable, 1);
        Page<LivroDTO> pageLivrosDto = new PageImpl(listaDto, pageable, 1);
        when(livroRepository.findAll(pageable)).thenReturn(pageLivros);
        assertEquals(pageLivrosDto, livroService.findAllLivros(pageable));
    }

    @Test
    @DisplayName("Deve retornar uma lista de livros filtradas por categoria")
    void deveRetornarUmaListaDeLivrosPorCategoria() {
        Pageable pageable = PageRequest.of(0, 1);
        List<LivroDTO> listaDto = List.of(criarLivroDto());
        List<Livro> listaLivroEntidade = listaDto.stream().map(LivroMapper::dtoToLivro).toList();
        Page<Livro> pageLivros = new PageImpl(listaLivroEntidade, pageable, 1);
        Page<LivroDTO> pageLivrosDto = new PageImpl(listaDto, pageable, 1);
        when(livroRepository.findByCategoriaNome("p", pageable)).thenReturn(pageLivros);
        assertEquals(pageLivrosDto, livroService.findLivroByCategoria("p", pageable));
    }

    @Test
    @DisplayName("Deve retornar uma lista de livros filtradas pelo nome do autor ou titulo do livro")
    void deveRetornarUmaListaDeLivrosPorAutorNomeouTitulo() {
        Pageable pageable = PageRequest.of(0, 1);
        List<LivroDTO> listaDto = List.of(criarLivroDto());
        List<Livro> listaLivroEntidade = listaDto.stream().map(LivroMapper::dtoToLivro).toList();
        Page<Livro> pageLivros = new PageImpl(listaLivroEntidade, pageable, 1);
        Page<LivroDTO> pageLivrosDto = new PageImpl(listaDto, pageable, 1);
        when(livroRepository.findByTituloOrAutorNome("p",
                pageable)).thenReturn(pageLivros);
        assertEquals(pageLivrosDto, livroService.findByTituloOrAutorNome("p", pageable));
    }

    @Test
    @DisplayName("Deve criar um livro")
    void deveCriarUmLivro() {
        when(livroRepository.save(any())).thenReturn(criarLivro());
        assertEquals(criarLivroDto(), livroService.saveLivro(criarLivroDto()));
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    void deveAtualizarUmLivro() {
        when(livroRepository.findById(1L)).thenReturn(Optional.ofNullable(criarLivro()));
        when(livroRepository.save(criarLivro())).thenReturn(criarLivro());
        assertEquals(criarLivroDto(), livroService.updateLivro(criarLivroDto()));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar um livro e não encontrá-lo ")
    void deveRetornarNotFoundExceptionAoTentarAtualizarENaoEncontrarLivro() {
        when(livroRepository.findById(criarLivro().getId())).thenThrow(NotFoundException.class);
        LivroDTO atualizar = LivroMapper.livroToDTO(criarLivro());
        assertThrows(NotFoundException.class, () -> livroService.updateLivro(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar um livro nulo")
    void deveRetornarBadRequestExceptionAoTentarCriarLivroSemTitulo() {
        when(livroRepository.save(any())).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> livroService.saveLivro(criarLivroDto()));
    }

    @Test
    @DisplayName("Deve apagar um livro")
    void deveApagarUmLivro() {
        when(livroRepository.findById(any())).thenReturn(Optional.of(criarLivro()));
        livroService.deleteLivro(1L);
        verify(livroRepository, times(1)).delete(criarLivro());
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar apagar livros e não encontra-lo")
    void deveRetornarNotFoundExceptionAoTentarApagarLivroENaoEncontrar() {
        when(livroRepository.findById(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> livroService.deleteLivro(criarLivro().getId()));
    }
}