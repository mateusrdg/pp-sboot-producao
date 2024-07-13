package com.pratopronto.dominio;

import com.pratopronto.dominio.enums.StatusEnum;

public class Order {

    private Long id;
    private StatusEnum status;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
