package com.pratopronto.dominio.adaptadores.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.dtos.UpdateOrderDTO;
import com.pratopronto.dominio.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProducaoServiceImpTest {

    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProducaoServiceImp producaoServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(producaoServiceImp, "endpoint", "http://localhost:4566/000000000000/queue");
    }

    @Test
    public void testAtualizarPedido() throws NotFoundException, JsonProcessingException {
        Long orderId = 1L;
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(StatusEnum.PRONTO);

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(updateOrderDTO.getStatus());

        when(objectMapper.writeValueAsString(any(Order.class))).thenReturn("orderJson");

        producaoServiceImp.atualizarPedido(orderId, updateOrderDTO);

        verify(queueMessagingTemplate, times(1)).send(anyString(), any(Message.class));
    }

    @Test
    public void testSendMessage() throws JsonProcessingException {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(StatusEnum.RECEBIDO);

        when(objectMapper.writeValueAsString(order)).thenReturn("orderJson");

        producaoServiceImp.sendMessage(order);

        verify(queueMessagingTemplate, times(1)).send(anyString(), any(Message.class));
    }

    @Test
    public void testSendMessageJsonProcessingException() throws JsonProcessingException {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(StatusEnum.EM_PREPARACAO);

        when(objectMapper.writeValueAsString(order)).thenThrow(new JsonProcessingException("error") {});

        assertThrows(RuntimeException.class, () -> producaoServiceImp.sendMessage(order));
    }
}
