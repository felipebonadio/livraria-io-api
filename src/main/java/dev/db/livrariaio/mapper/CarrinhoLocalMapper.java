package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.CarrinhoLocalDTO;
import dev.db.livrariaio.model.Carrinho;

public class CarrinhoLocalMapper {

    public static CarrinhoLocalDTO carrinhoToDTO(Carrinho carrinho) {
        return CarrinhoLocalDTO.builder()
                .id(carrinho.getId()).build();
    }

    public static Carrinho dtoToCarrinho(CarrinhoLocalDTO carrinhoLocalDTO) {
        return Carrinho.builder()
                .id(carrinhoLocalDTO.getId())
                .build();
    }

}
