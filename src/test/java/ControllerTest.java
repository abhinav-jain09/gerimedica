
import com.gerimedica.assignment.controller.ItemController;
import com.gerimedica.assignment.entity.Item;
import com.gerimedica.assignment.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    void uploadFile_ShouldUploadAndSaveItems() throws Exception {
        String csvContent = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
                "ZIB,ZIB001,271636001,Polsslag regelmatig,The long description is necessary,01-01-2019,,1\n";
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        mockMvc.perform(multipart("/items/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded successfully!"));

        verify(itemService, times(1)).uploadFile(any());
    }

    @Test
    void getAllItems_ShouldReturnAllItems() throws Exception {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setCode("123");
        items.add(item1);

        when(itemService.findAll()).thenReturn(items);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("123"));

        verify(itemService, times(1)).findAll();
    }

    @Test
    void getItemByCode_ShouldReturnItem() throws Exception {
        Item item = new Item();
        item.setCode("123");

        when(itemService.findByCode("123")).thenReturn(Optional.of(item));

        mockMvc.perform(get("/items/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("123"));

        verify(itemService, times(1)).findByCode("123");
    }

    @Test
    void deleteAllItems_ShouldDeleteAllItems() throws Exception {
        mockMvc.perform(delete("/items"))
                .andExpect(status().isOk())
                .andExpect(content().string("All items deleted successfully!"));

        verify(itemService, times(1)).deleteAll();
    }
}
