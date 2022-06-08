package com.developer.backend.controller;

import com.developer.backend.dto.ItemDTO;
import com.developer.backend.entity.Item;
import com.developer.backend.exception.ItemNotFoundException;
import com.developer.backend.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ItemControllerTest {

    public static final int ID = 1;
    public static final String TEST_1 = "test1";
    public static final long ID1 = 1L;
    @InjectMocks
    private ItemController itemController;

    @Mock
    private ModelMapper mapper;

    @Mock
    private ItemService itemService;

    private ItemDTO itemDTO = new ItemDTO();
    private Item item = new Item();

    @BeforeEach
    void setUp() {
        openMocks(this);
        startItems();
    }

    @Test
    void CreateItemWithSuccess() {
        when(itemService.create(any())).thenReturn(item);
        when(mapper.map(any(),any())).thenReturn(itemDTO);

        ItemDTO response = itemController.createItem(itemDTO);

        assertNotNull(response);
        assertEquals(ItemDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TEST_1, response.getDescription());
    }

    @Test
    void updateWithSuccess() throws ItemNotFoundException {
        when(itemService.update(anyLong())).thenReturn(new ItemDTO(1, "test1", true));

        ItemDTO response = itemController.update(ID1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertTrue(response.isCompleted());

    }

    @Test
    void whenUpdateReturnAItemNotFoundException() throws ItemNotFoundException {
        when(itemService.update(anyLong())).thenThrow(new ItemNotFoundException());

        try{
            itemController.update(anyLong());
        }catch (Exception e){
            assertEquals(ItemNotFoundException.class, e.getClass());
            assertEquals("Item not found", e.getMessage());
        }
    }

    @Test
    void deleteOneItemWithSuccess() {
        doNothing().when(itemService).delete(anyLong());

        ResponseEntity<ItemDTO> response = itemController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemService, times(1)).delete(ID);
    }

    @Test
    void deleteAllCompletedWithSuccess() {
        doNothing().when(itemService).deleteAllCompleted();

        ResponseEntity<ItemDTO> response = itemController.deleteAllCompleted();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemService, times(1)).deleteAllCompleted();
    }

    @Test
    void deleteAllWithSuccess() {
        doNothing().when(itemService).deleteAll();

        ResponseEntity<ItemDTO> response = itemController.deleteAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemService, times(1)).deleteAll();
    }

    @Test
    void whenFindAllThenReturnAList() {
        when(itemService.findAll()).thenReturn(List.of(itemDTO));

        ResponseEntity<List<ItemDTO>> response = itemController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(1, response.getBody().get(0).getId());
        assertEquals(ItemDTO.class, response.getBody().get(0).getClass());
    }

    private void startItems() {
        item.setId(ID);
        item.setDescription(TEST_1);
        item.setCompleted(false);

        item.getDescription();
        item.isCompleted();

        itemDTO.setId(ID);
        itemDTO.setDescription(TEST_1);
        itemDTO.setCompleted(false);
    }
}