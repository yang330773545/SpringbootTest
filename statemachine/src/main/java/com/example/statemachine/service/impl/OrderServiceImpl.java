package com.example.statemachine.service.impl;

import com.example.statemachine.component.Events;
import com.example.statemachine.component.States;
import com.example.statemachine.entity.Order;
import com.example.statemachine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    //@Autowired
    //private StateMachine<States, Events> stateMachine;
    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;
    @Autowired
    private StateMachinePersister<States, Events, Order> persister;

    public static final String stateMachineId = "stateMachine";

    /**
     * 模拟数据
     */
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order pay(int id) {
        Order order = orders.get(id);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试支付 id=" + id);
        Message message = MessageBuilder.withPayload(Events.PAYED).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 支付失败, 状态异常 id=" + id);
        }
        return orders.get(id);
    }


    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param order
     * @return
     */
    /*
    private synchronized boolean sendEvent(Message<Events> message, Order order) {
        boolean result = false;
        try {
            stateMachine.start();
            //尝试恢复状态机状态
            persister.restore(stateMachine, order);
            //添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = stateMachine.sendEvent(message);
            //持久化状态机状态
            persister.persist(stateMachine, order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stateMachine.stop();
        }
        return result;
    }
    */
    private boolean sendEvent(Message<Events> message, Order order) {
        synchronized (String.valueOf(order.getId()).intern()) {
            boolean result = false;
            StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine(stateMachineId);
            System.out.println("id=" + order.getId() + " 状态机 stateMachine" + stateMachine);
            try {
                stateMachine.start();
                //尝试恢复状态机状态
                persister.restore(stateMachine, order);
                System.out.println("id=" + order.getId() + " 状态机 stateMachine id=" + stateMachine.getId());
                //添加延迟用于线程安全测试
                Thread.sleep(1000);
                result = stateMachine.sendEvent(message);
                //持久化状态机状态
                persister.persist(stateMachine, order);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stateMachine.stop();
            }
            return result;
        }
    }
}