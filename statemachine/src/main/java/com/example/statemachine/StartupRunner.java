package com.example.statemachine;

import com.example.statemachine.component.Events;
import com.example.statemachine.component.States;
import com.example.statemachine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner{
    //@Autowired
    //private StateMachine<States, Events> stateMachine;

    public static final String stateMachineId = "stateMachine";

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;
    @Autowired
    private StateMachinePersister<States, Events, Order> persister;

    @Override
    public void run(String... args) throws Exception {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine(stateMachineId);
        Order order = new Order();
        order.setId(123456L);
        order.setStatus(States.WAIT_RECEIVE);
        persister.restore(stateMachine, order);
        Message message = MessageBuilder.withPayload(Events.RECEIVED).setHeader("order", order).build();
        boolean result = stateMachine.sendEvent(message);
        if(result){
            System.out.println("流程正确");
        } else{
            System.out.println("流程错误");
        }
        /*
        stateMachine.start();
        stateMachine.sendEvent(Events.PAYED);
        stateMachine.sendEvent(Events.DELIVERY);
        stateMachine.sendEvent(Events.RECEIVED);
        System.out.println("流程已结束");
        */
    }
}
