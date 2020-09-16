package com.example.statemachine.config;

import com.example.statemachine.component.Events;
import com.example.statemachine.component.States;
import com.example.statemachine.entity.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;


/**
 * 注释掉的为单注册机代码
 */
@Configuration
//@EnableStateMachine
@EnableStateMachineFactory(name = "stateMachineFactory")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    /**订单状态机ID*/
    public static final String stateMachineId = "stateMachineId";

    /**
     * 配置初始状态和所有状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates().initial(States.WAIT_PAYMENT).states(EnumSet.allOf(States.class));
    }

    /**
     * 配置转换关系事件
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(States.WAIT_PAYMENT).target(States.WAIT_DELIVER)
                .event(Events.PAYED)
                .and()
                .withExternal()
                .source(States.WAIT_DELIVER).target(States.WAIT_RECEIVE)
                .event(Events.DELIVERY)
                .and()
                .withExternal()
                .source(States.WAIT_RECEIVE).target(States.FINISH)
                .event(Events.RECEIVED);
    }

    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     * @return
     */
    @Bean
    public StateMachinePersister<States, Events, Order> persister(){
        return new DefaultStateMachinePersister<>(new StateMachinePersist<States, Events, Order>() {
            @Override
            public void write(StateMachineContext<States, Events> context, Order order) throws Exception {
                //此处并没有进行持久化操作
            }

            @Override
            public StateMachineContext<States, Events> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                //return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
                return new DefaultStateMachineContext<>(order.getStatus(), null, null, null, null, stateMachineId);
            }
        });
    }
}
