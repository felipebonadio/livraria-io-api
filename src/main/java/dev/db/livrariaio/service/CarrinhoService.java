package dev.db.livrariaio.service;

import java.util.List;
import java.util.Optional;

import dev.db.livrariaio.dto.CarrinhoLocalDTO;
import dev.db.livrariaio.mapper.CarrinhoLocalMapper;
import org.springframework.stereotype.Service;

import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.CarrinhoMapper;
import dev.db.livrariaio.mapper.ItemMapper;
import dev.db.livrariaio.model.Carrinho;

import dev.db.livrariaio.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public CarrinhoDTO findCarrinhoById(Long id) {
        Optional<CarrinhoDTO> carrinhoToFind = carrinhoRepository.findById(id).map(CarrinhoMapper::carrinhoToDTO);
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException("Carrinho não encontrado.");
        }
        return carrinhoToFind.get();
    }

    public List<CarrinhoDTO> findAllCarrinhos() {
        return carrinhoRepository.findAll().stream().map(CarrinhoMapper::carrinhoToDTO).toList();
    }

    public CarrinhoDTO saveCarrinho(CarrinhoDTO carrinhoDTO) {
        Carrinho carrinho = CarrinhoMapper.dtoToCarrinho(carrinhoDTO);
        carrinho.setId(null);
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinho));
    }

    public CarrinhoLocalDTO criarCarrinho() {
        return CarrinhoLocalMapper.carrinhoToDTO(carrinhoRepository.save(new Carrinho()));
    }

    public CarrinhoDTO adicionarItemCarrinho(Long carrinhoId, ItemDTO itemDTO) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(carrinhoId);
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o carrinho com o ID: " + carrinhoId + ", pois o mesmo não existe.");
        }
        carrinhoToFind.get().adicionarItem(ItemMapper.dtoToItem(itemDTO));
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinhoToFind.get()));
    }

    public CarrinhoDTO removerItemCarrinho(Long carrinhoId, ItemDTO itemDTO) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(carrinhoId);
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o carrinho com o ID: " + carrinhoId + ", pois o mesmo não existe.");
        }
        carrinhoToFind.get().removerItem(itemDTO.getId());
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinhoToFind.get()));
    }

    public void deleteCarrinho(Long id) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(id);
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException("Não foi possível excluir carrinho");
        }
        carrinhoRepository.deleteById(id);
    }
}