package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String addressString;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;
}
