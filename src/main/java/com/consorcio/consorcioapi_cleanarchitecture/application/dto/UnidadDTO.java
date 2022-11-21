package com.consorcio.consorcioapi_cleanarchitecture.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnidadDTO {

    private Integer identificador;

    private String piso;

    private String numero;

    private String habitado;

    private Integer codigoEdificio;

    private List<String> documentoDuenios;

    private List<String> documentoInquilinos;
}
