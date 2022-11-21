package com.consorcio.consorcioapi_cleanarchitecture.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Reclamo {

    private Integer idReclamo;

    private Persona documento;

    private Edificio codigo;

    private String ubicacion;

    private String descripcion;

    private Unidad identificador;

    private Estado estado = Estado.nuevo;

    private List<Imagen> imagenes;

    public void agregarImagen(String direccion, String tipo) {
        Imagen imagen = Imagen.builder().path(direccion).tipo(tipo).idReclamo(this).build();
        imagenes.add(imagen);
    }

    public void eliminarImagen(Integer numero) {
        this.imagenes =
                imagenes.stream().filter(i -> !Objects.equals(i.getNumero(), numero)).collect(Collectors.toList());
    }

    public void cambiarEstado(Estado estado) {
        this.estado = estado;
    }
}