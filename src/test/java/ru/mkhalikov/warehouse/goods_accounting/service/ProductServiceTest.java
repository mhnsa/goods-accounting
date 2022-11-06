package ru.mkhalikov.warehouse.goods_accounting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mkhalikov.warehouse.goods_accounting.def_value.ProductEntityDefault;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.repository.ProductRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.impl.ProductServiceImpl;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Тесты сервиса для работы с информацией о товарах на складе
 **/
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getByIdPositiveTest() {
        var productEntity = ProductEntityDefault.get();

        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(productEntity));

        var result = productService.getById(1);

        assert productEntity != null;

        assertEquals(productEntity.getId(), result.getId());
        assertEquals(productEntity.getName(), result.getName());
        assertEquals(productEntity.getArticle(), result.getArticle());
        assertEquals(productEntity.getQuantity(), result.getQuantity());
        assertEquals(productEntity.getLastSalePrice(), result.getLastSalePrice());
        assertEquals(productEntity.getLastPurchasePrice(), result.getLastPurchasePrice());
    }

    @Test
    public void getByIdNegativeTest() {
        assertThrows(NotFoundException.class, () -> productService.getById(1));
    }
}
