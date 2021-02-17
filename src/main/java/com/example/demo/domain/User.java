package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private String userD;
    private String tokenD;
}
