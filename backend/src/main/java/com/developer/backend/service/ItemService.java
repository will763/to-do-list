package com.developer.backend.service;

import com.developer.backend.dto.ItemDTO;
import com.developer.backend.entity.Item;
import com.developer.backend.exception.ItemNotFoundException;
import com.developer.backend.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper mapper;

    public Item create(ItemDTO itemDTO){
        return itemRepository.save(mapper.map(itemDTO, Item.class));
    }

    public List<ItemDTO> findAll(){
        List<Item> list = itemRepository.findAll();
        return list.stream().map(x -> mapper.map(x, ItemDTO.class)).collect(Collectors.toList());
    }

    public ItemDTO update(long id) throws ItemNotFoundException {
        return itemRepository.findById(id).map( record -> {
                    record.setCompleted(true);
                    return mapper.map(itemRepository.save(record),ItemDTO.class);
        }).orElseThrow(ItemNotFoundException::new);
    }

    public void delete(long id) {itemRepository.deleteById(id);}

    public void deleteAllCompleted(){
        itemRepository.deleteItemByCompletedIsTrue();
    }

    public void deleteAll() {itemRepository.deleteAll();}

}
