package com.consorcio.consorcioapi_cleanarchitecture.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Data
public class Edificio {

    private Integer codigo;

    private String nombre;

    private String direccion;

    private List<Unidad> unidades;

    public void agregarUnidad(Unidad unidad) {
        unidades.add(unidad);
    }

    public void eliminarUnidad(Unidad unidad) {
        unidades.remove(unidad);
    }

    public Set<Persona> habilitados() {
        Set<Persona> habilitados = new HashSet<>();
        for (Unidad unidad : unidades) {
            List<Persona> duenios = new ArrayList<>(unidad.getDuenios());
            habilitados.addAll(duenios);
            List<Persona> inquilinos = new ArrayList<>(unidad.getInquilinos());
            habilitados.addAll(inquilinos);
        }
        return habilitados;
    }

    public Set<Persona> duenios() {
        Set<Persona> resultado = new HashSet<>();
        for (Unidad unidad : unidades) {
            List<Persona> duenios = new ArrayList<>(unidad.getDuenios());
            resultado.addAll(duenios);
        }
        return resultado;
    }

    public Set<Persona> habitantes() {
        Set<Persona> resultado = new HashSet<>();
        for (Unidad unidad : unidades) {
            if (unidad.estaHabitado()) {
                List<Persona> inquilinos = new ArrayList<>(unidad.getInquilinos());
                if (inquilinos.size() > 0)
                    resultado.addAll(inquilinos);
                else {
                    List<Persona> duenios = new ArrayList<>(unidad.getDuenios());
                    resultado.addAll(duenios);
                }
            }
        }
        return resultado;
    }
}
