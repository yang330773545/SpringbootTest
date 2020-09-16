package com.example.statemachine.entity;

import com.example.statemachine.component.States;

public class Order {
    private Long id;
    private States status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public States getStatus() {
        return status;
    }

    public void setStatus(States status) {
        this.status = status;
    }
}
