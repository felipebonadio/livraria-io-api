package dev.db.livrariaio.service;

import java.util.List;
import java.util.Optional;

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

    public CarrinhoService(CarrinhoRepository carrinhoRepository){
        this.carrinhoRepository = carrinhoRepository;
    }

    public CarrinhoDTO findCarrinhoById(Long id) {
        Optional<CarrinhoDTO> carrinhoToFind = carrinhoRepository.findById(id).map(CarrinhoMapper::carrinhoToDTO);
        if(carrinhoToFind.isEmpty()) {
            throw new NotFoundException("Carrinho não encontrado.");
        }
        return carrinhoToFind.get();
    }

    public List<CarrinhoDTO> findAllCarrinhos() {
        return carrinhoRepository.findAll().stream().map(CarrinhoMapper::carrinhoToDTO).toList();
    }

    public CarrinhoDTO saveCarrinho(CarrinhoDTO carrinhoDTO){
        Carrinho carrinho = CarrinhoMapper.dtoToCarrinho(carrinhoDTO);
        carrinho.setId(null);
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinho));
    }

    public CarrinhoDTO adicionarItemCarrinho(CarrinhoDTO carrinhoDTO, ItemDTO itemDTO) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(carrinhoDTO.getId());
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException("Não foi possível atualizar o carrinho com o ID: " + carrinhoDTO.getId() + ", pois o mesmo não existe.");
        }
        Carrinho carrinhoToUpdate = carrinhoToFind.get();
        carrinhoToUpdate.adicionarItem(ItemMapper.dtoToItem(itemDTO));
        
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinhoToUpdate));
    }

    public CarrinhoDTO removerItemCarrinho(CarrinhoDTO carrinhoDTO, ItemDTO itemDTO) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(carrinhoDTO.getId());
        if (carrinhoToFind.isEmpty()) {
            throw new NotFoundException("Não foi possível atualizar o carrinho com o ID: " + carrinhoDTO.getId() + ", pois o mesmo não existe.");
        }
        Carrinho carrinhoToUpdate = carrinhoToFind.get();
        carrinhoToUpdate.removerItem(ItemMapper.dtoToItem(itemDTO));
        
        return CarrinhoMapper.carrinhoToDTO(carrinhoRepository.save(carrinhoToUpdate));
    }

    public void deleteCarrinho(Long id) {
        Optional<Carrinho> carrinhoToFind = carrinhoRepository.findById(id);
        if(carrinhoToFind.isEmpty()){
            carrinhoRepository.deleteById(id);
        }
    }
    

}
