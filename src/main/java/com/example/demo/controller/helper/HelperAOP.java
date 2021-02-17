package com.example.demo.controller.helper;

import com.example.demo.domain.Student;
import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@EnableAspectJAutoProxy
public class HelperAOP {


    final AddressRepository addressRepository;

    public HelperAOP(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Before("execution(* com.example.demo.service.StudentService.getClasses())")
    public void log(){

        log.info("Before");
    }

    @After("execution(* com.example.demo.service.StudentService.getClasses())")
    public void log1(){

        log.info("After");
    }

    @Before("execution(* com.example.demo.service.StudentService.getStudentByID(..))")
    public void log3(){

        log.info("Before");
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.StudentService.getStudentByID(..)) && args(yourString,..)"
            ,argNames = "joinPoint,yourString,result", returning = "result")
    public void log4(JoinPoint joinPoint, String yourString, Student result){


        log.info("After ID: {}", yourString);
        log.info(result.toString());
    }

/*
    @Before("execution(* com.example.demo.repository.CustomerRepository.save(..))  && args(customer,..)")
    public void log5(Customer customer){

        log.info("Before");
        log.info(customer.toString());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.repository.CustomerRepository.save(..)) && args(customer,..)")
    public void log6(JoinPoint joinPoint, Customer customer){

        addressRepository.save(Address.builder()
                .addressString("hola")
                .customer(customer).build());
        log.info("After");
        log.info(customer.toString());
    }*/

    @Around("execution(* com.example.demo.repository.CustomerRepository.save(..))")
    public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable
    {
        //BEFORE
        Object result;
        Customer customer = (Customer) joinPoint.getArgs()[0];
        boolean isInsert = customer.getId() == null;
        //
        try {
            result = joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        if(isInsert){
            addressRepository.save(Address.builder()
                    .addressString("hola")
                    .customer(customer).build());
        }
        return result;
    }
}
