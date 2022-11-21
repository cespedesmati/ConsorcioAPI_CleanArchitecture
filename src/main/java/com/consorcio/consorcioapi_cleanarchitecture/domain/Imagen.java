package com.consorcio.consorcioapi_cleanarchitecture.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Imagen {

    private Integer numero;

    private String path;

    private String tipo;

    private Reclamo idReclamo;
}
