package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.services.IService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ProductControllerTest {
    @Mock
    private IService<Product> productService;
    @Mock
    private Model model;
    private ProductController productController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));
    }

    @Test
    public void home() {
        List<Product> productsService = new ArrayList<>();
        productsService.add(new Product());
        productsService.add(new Product());
        when(productService.listAll()).thenReturn(productsService);

        String home = productController.home(model);
        assertEquals("home", home);
        verify(productService, times(1)).listAll();

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Product>> productCaptor = ArgumentCaptor.forClass(List.class);
        verify(model, times(1)).addAttribute(eq("products"), productCaptor.capture());
        List<Product> products2 = productCaptor.getValue();
        assertEquals(2, products2.size());
    }
}