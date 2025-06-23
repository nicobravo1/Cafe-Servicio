package com.cafeteria.cafe_service.controller;

import com.cafeteria.cafe_service.service.CafeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CafeController.class)
public class CafeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CafeService cafeService;

    @Test
    public void testObtenerCafesVacio() throws Exception {
        when(cafeService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/cafes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
