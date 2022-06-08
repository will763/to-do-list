package com.developer.backend.service;

import com.developer.backend.dto.ItemDTO;
import com.developer.backend.entity.Item;
import com.developer.backend.exception.ItemNotFoundException;
import com.developer.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ItemServiceTest {

    public static final int ID = 1;
    public static final String TEST_1 = "test 1";
    public static final String TEST_11 = "test1";

    @InjectMocks
    private ItemService service;

    @Mock
    private ItemRepository repository;

    @Mock
    private ModelMapper mapper;

    private ItemDTO itemDTO;
    private Item item;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startItems();
    }

    @Test
    void whenCreateReturnAnInstanceUserDTO() {
        when(repository.save(any())).thenReturn(item);

        Item response = service.create(itemDTO);

        assertNotNull(response);
        assertEquals(Item.class, response.getClass());
        assertEquals(1,response.getId());
    }

    @Test
    void  WhenFindAllThenReturnAnList() {
        when(repository.findAll()).thenReturn(List.of(item));
        when(mapper.map(any(),any())).thenReturn(itemDTO);

        List<ItemDTO> list = service.findAll();

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0).getId());
    }

    @Test
    void whenUpdateThenReturnSuccess() throws ItemNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(item));
        when(repository.save(any())).thenReturn(new Item(1,"test1",true));
        when(mapper.map(any(),any())).thenReturn(new ItemDTO(1,"test1",true));

        ItemDTO response = service.update(1);

        assertNotNull(response);
        assertTrue(response.isCompleted());
        assertEquals(ItemDTO.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnItemNotFoundException() throws ItemNotFoundException {
        when(repository.save(any())).thenReturn(new Item(1,"test1",true));
        when(mapper.map(any(),any())).thenReturn(new ItemDTO(1,"test1",true));

        try{
            ItemDTO response = service.update(1);
        }catch (Exception e) {
           assertEquals(ItemNotFoundException.class, e.getClass());
           assertEquals("Item not found", e.getMessage());
        }
    }

    @Test
    void deleteItemWithSuccess() {
        service.delete(ID);
        verify(repository,times(1)).deleteById(anyLong());
    }

    @Test
    void deleteAllItemsCompletedWithSuccess() {
        service.deleteAllCompleted();
        verify(repository,times(1)).deleteItemByCompletedIsTrue();
    }

    @Test
    public void deleteAllItemsWithSuccess(){
        service.deleteAll();
        verify(repository,times(1)).deleteAll();
    }

    private void startItems() {
        item = new Item(ID,TEST_1, false);
        itemDTO = new ItemDTO(ID, TEST_11, false);

        item.toString();
        item.hashCode();
        item.equals(item);
    }
}