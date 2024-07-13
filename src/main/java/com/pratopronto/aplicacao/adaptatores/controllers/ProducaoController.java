package com.pratopronto.aplicacao.adaptatores.controllers;

import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.dtos.UpdateOrderDTO;
import com.pratopronto.dominio.portas.interfaces.ProducaoServicePort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producao")
public class ProducaoController {

    private final ProducaoServicePort producaoServicePort;

    public ProducaoController(ProducaoServicePort producaoServicePort) {
        this.producaoServicePort = producaoServicePort;
    }

    @PutMapping(value = "/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDTO updateOrderDTO)throws NotFoundException {
        producaoServicePort.atualizarPedido(id, updateOrderDTO);
    }
}
