package com.example.demo.service;

import com.example.demo.dao.EmployeeMapper;
import com.example.demo.entry.Employee;
import com.example.demo.entry.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DemoService {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 相当于 select * from genera where pass = "lalala";
    private String test(){
        EmployeeExample goodsExample = new EmployeeExample();
        /* 然后创建criteria对象 */
        EmployeeExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andPassEqualTo("lalala");
        List<Employee> goodses = employeeMapper.selectByExample(goodsExample);
        System.out.println(goodses);
        return "dd";
    }
}
