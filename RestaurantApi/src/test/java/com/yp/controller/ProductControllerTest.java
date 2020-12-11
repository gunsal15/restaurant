package com.yp.controller;

import com.yp.builder.ProductDtoBuilder;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductDto productDto = new ProductDtoBuilder().build();


    @Before
    public void setUp() {

    }

    @Test
    public void shouldgetAuthorityWithId() {
        productController.getProduct(1);

        verify(productService,times(1)).getProduct(1);
    }

    @Test
    public void getAuthorityList(){
        productController.getAllProducts();
        verify(productService,times(1)).getAllProducts();
    }

    @Test
    public void shouldSaveWithDto() {
        productController.addProduct(productDto);
        verify(productService, times(1)).addProduct(productDto);

    }

    @Test
    public void shouldEditWithDtop(){
        productController.editProduct(1, productDto);
        verify(productService, times(1)).editProduct(1, productDto);

    }

    @Test
    public void shouldDeleteWithId() {
        productController.deleteProduct(1);
        verify(productService, times(1)).deleteProduct(1);
    }
}