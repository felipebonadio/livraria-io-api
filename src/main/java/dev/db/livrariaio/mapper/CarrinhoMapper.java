package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.model.Carrinho;

public class CarrinhoMapper {
    public static CarrinhoDTO carrinhoToDTO(Carrinho carrinho) {
        return CarrinhoDTO.builder()
                .id(carrinho.getId())
                .itensDTO(carrinho.getItens().stream().map(ItemMapper::itemToDTO).toList())
                .precoTotal(carrinho.somarPrecoTotal())
                .build();
    }

    public static Carrinho dtoToCarrinho(CarrinhoDTO carrinhoDTO) {
        return Carrinho.builder()
                .id(carrinhoDTO.getId())
                .itens(carrinhoDTO.getItensDTO().stream().map(ItemMapper::dtoToItem).toList())
                .build();
    }
}
