package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.model.Item;

public class ItemMapper {
    public static ItemDTO itemToDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .precoItem(item.getPrecoItem())
                .quantidadeDeLivros(item.getQuantidadeDeLivros())
                .livroCarrinhoDTO(LivroCarrinhoMapper.livroToDTO(item.getLivro()))
                .build();
    }

    public static Item dtoToItem(ItemDTO itemDTO) {
        return Item.builder().id(itemDTO.getId())
                .precoItem(itemDTO.getPrecoItem())
                .quantidadeDeLivros(itemDTO.getQuantidadeDeLivros())
                .livro(LivroCarrinhoMapper.dtoToLivro(itemDTO.getLivroCarrinhoDTO()))
                .build();
    }

}
