

import com.gerimedica.assignment.entity.Item;
import com.gerimedica.assignment.repository.ItemRepository;
import com.gerimedica.assignment.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllItems() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setCode("123");
        items.add(item1);

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> result = itemService.findAll();
        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getCode());
    }

    @Test
    void findByCode_ShouldReturnItem() {
        Item item = new Item();
        item.setCode("123");

        when(itemRepository.findById("123")).thenReturn(Optional.of(item));

        Optional<Item> result = itemService.findByCode("123");
        assertTrue(result.isPresent());
        assertEquals("123", result.get().getCode());
    }

    @Test
    void saveAll_ShouldSaveItems() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setCode("123");
        items.add(item1);

        itemService.saveAll(items);
        verify(itemRepository, times(1)).saveAll(items);
    }

    @Test
    void deleteAll_ShouldDeleteAllItems() {
        itemService.deleteAll();
        verify(itemRepository, times(1)).deleteAll();
    }
}
