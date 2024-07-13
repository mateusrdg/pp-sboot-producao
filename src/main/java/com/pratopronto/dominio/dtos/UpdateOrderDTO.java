package com.pratopronto.dominio.dtos;

import com.pratopronto.dominio.enums.StatusEnum;

import java.time.LocalDateTime;

public class UpdateOrderDTO {
    private StatusEnum status;

    public UpdateOrderDTO() {
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
