package com.example.demo.domain;

import com.example.demo.domain.valinterface.LDArgentina;
import com.example.demo.domain.valinterface.LDColombia;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DemoVal {

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚ0-9\\s]{1,150}$", groups = LDColombia.class)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{0,22}$", groups = LDColombia.class)
    @NotNull(groups = LDColombia.class)
    @Size(min = 4, max = 9, groups = LDColombia.class)
    @Size(min = 10, max = 20, groups = LDArgentina.class)
    private String string;

    @Min(value = 5000, groups = LDColombia.class)
    @Max(value = 1000, groups = LDArgentina.class)
    private Integer numero;
}
