package com.example.statemachine.component;

/**
 * 状态枚举 模拟订单状态
 */
public enum States {
    WAIT_PAYMENT, WAIT_DELIVER, WAIT_RECEIVE, FINISH;
}
