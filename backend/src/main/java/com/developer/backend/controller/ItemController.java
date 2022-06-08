package com.developer.backend.controller;

import com.developer.backend.dto.ItemDTO;
import com.developer.backend.exception.ItemNotFoundException;
import com.developer.backend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody @Valid ItemDTO itemDTO){
       return mapper.map(itemService.create(itemDTO), ItemDTO.class);
    }

    @PutMapping(value = "/{id}")
    public ItemDTO update(@PathVariable("id") Long id) throws ItemNotFoundException {
        return itemService.update(id);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ItemDTO> delete(@PathVariable long id){
          itemService.delete(id);
          return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<ItemDTO> deleteAllCompleted(){
        itemService.deleteAllCompleted();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<ItemDTO> deleteAll(){
        itemService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll(){
        List<ItemDTO> list = itemService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
