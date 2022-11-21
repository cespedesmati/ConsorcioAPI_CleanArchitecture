package com.consorcio.consorcioapi_cleanarchitecture.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Persona {

    protected String documento;

    protected String nombre;

    private String contrasenia = "1234";

    private Boolean admin = false;
}
