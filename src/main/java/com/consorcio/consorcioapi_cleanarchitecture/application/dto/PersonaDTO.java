package com.consorcio.consorcioapi_cleanarchitecture.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDTO {

    private String documento;

    private String nombre;

    private String contrasenia;

    private Boolean admin;
}

