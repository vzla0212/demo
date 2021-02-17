package com.example.demo.controller;

import com.example.demo.domain.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalcController {

    @GetMapping("/calc")
    public List<Float> calc(@RequestBody List<Test> tests){


        return calcs(tests);
    }

    private List<Float> calcs(List<Test> tests) {

        return tests.stream().map(this::calcOper).collect(Collectors.toList());
    }

    private float calcOper(Test test) {

        switch (test.getOper())
        {
            case ADDITION:  return test.getNum1()+ test.getNum2();
            case SUBSTRACTION:  return test.getNum1()- test.getNum2();
            case MULTIPLICATION:  return test.getNum1()* test.getNum2();
            case DIVISION:
                if(test.getNum2() != 0){
                    return (float)test.getNum1()/test.getNum2();
                }
                break;
            default: return 0;
        }
        return 0;
    }
}
