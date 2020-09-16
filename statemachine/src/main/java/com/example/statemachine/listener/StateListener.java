package com.example.statemachine.listener;

import com.example.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component
//@WithStateMachine
@WithStateMachine(name = "stateMachine")
public class StateListener {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition() {
        System.out.println("用户已支付。");
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition() {
        System.out.println("用户待收货。");
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition() {
        System.out.println("用户已收获。");
        return true;
    }
}
