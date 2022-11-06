package ru.mkhalikov.warehouse.goods_accounting.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;
import ru.mkhalikov.warehouse.goods_accounting.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Интеграционные тесты для контроллера складов
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        warehouseRepository.deleteAll();
    }

    @Test
    public void givenWarehouseObject_whenCreateWarehouse_thenReturnSavedWarehouse() throws Exception{

        // given - precondition or setup
        WarehouseEntity warehouse = WarehouseEntity.builder()
                .name("Central Warehouse")
                .build();

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(warehouse)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(warehouse.getName())));

    }

    @Test
    public void givenListOfWarehouse_whenGetAll_thenReturnWarehouseList() throws Exception{
        // given - precondition or setup
        List<WarehouseEntity> listOfWarehouses = new ArrayList<>();
        listOfWarehouses.add(WarehouseEntity.builder().name("Central").build());
        listOfWarehouses.add(WarehouseEntity.builder().name("South").build());
        warehouseRepository.saveAll(listOfWarehouses);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/warehouse"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfWarehouses.size())));

    }

}
