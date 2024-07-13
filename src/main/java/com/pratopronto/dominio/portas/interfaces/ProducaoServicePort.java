package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.dtos.UpdateOrderDTO;

public interface ProducaoServicePort {
    void atualizarPedido(Long id, UpdateOrderDTO produtoDTO);

    void sendMessage(Order order);
}
