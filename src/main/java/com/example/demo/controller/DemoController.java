package com.example.demo.controller;

import com.example.demo.domain.DemoVal;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Validated
@RestController
@CacheConfig
@RequestMapping("/demoValidation")
public class DemoController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    StudentService studentService;

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @GetMapping("/calc")
    public String calc(@RequestBody DemoVal demoVal) {

        Set<Class<?>> allClasses = studentService.getClasses();

        allClasses.forEach(cls -> {
            log.info("########Validacion: {}", cls.getName());
            Set<ConstraintViolation<DemoVal>> s = validconst(demoVal, cls);

            s.forEach(p -> log.info(p.toString()));
        });

        return "FLOAT";
    }

    @GetMapping("/get")
    public String calc() {

        return hola();
    }


    private <T> Set<ConstraintViolation<T>> validconst(T t, Class<?> cls) {

        return validator.validate(t, cls);
    }

    @Cacheable(cacheNames = "hola")
    public String hola(){
        simulateSlowService();
        log.info("no cache");
        return "HolaMundo";
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
