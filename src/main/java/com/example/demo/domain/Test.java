package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Test {

    @NotNull
    private int num1;
    @NotNull
    private int num2;
    private Operation oper;

}
