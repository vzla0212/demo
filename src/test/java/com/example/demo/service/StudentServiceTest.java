package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceTest {

    @Test
    void test(){

        assertEquals("1aaa", "1a"+"aa", "");
    }

}