package com.pratopronto.aplicacao.adaptatores.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.dominio.dtos.UpdateOrderDTO;
import com.pratopronto.dominio.enums.StatusEnum;
import com.pratopronto.dominio.portas.interfaces.ProducaoServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProducaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProducaoServicePort producaoServicePort;

    @InjectMocks
    private ProducaoController producaoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(producaoController).build();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(StatusEnum.PRONTO);

        doNothing().when(producaoServicePort).atualizarPedido(anyLong(), any(UpdateOrderDTO.class));

        mockMvc.perform(put("/producao/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateOrderDTO)))
                .andExpect(status().isOk());
    }
}
