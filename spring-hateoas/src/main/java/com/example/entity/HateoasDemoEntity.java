package com.example.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class HateoasDemoEntity extends RepresentationModel<HateoasDemoEntity> {
    private String context;

    // @JsonCreator作用就是指定反序列化时用的无参构造函数
    // @JsonProperty作用是把该属性的名称序列化为另外一个名称
    @JsonCreator
    public HateoasDemoEntity(@JsonProperty("context") String context){
        this.context = context;
    }

    public String getContext() {
        return this.context;
    }
}
