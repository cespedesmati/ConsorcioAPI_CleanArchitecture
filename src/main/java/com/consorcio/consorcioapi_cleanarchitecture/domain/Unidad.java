package com.consorcio.consorcioapi_cleanarchitecture.domain;

import com.consorcio.consorcioapi_cleanarchitecture.application.exception.UnidadException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unidad {

    private Integer identificador;

    private String piso;

    private String numero;

    private String habitado;

    private Set<Persona> duenios = new HashSet<>();

    private Set<Persona> inquilinos = new HashSet<>();

    public void transferir(Persona persona) {
        duenios = new HashSet<>();
        duenios.add(persona);
    }

    public void agregarDuenio(Persona duenio) {
        duenios.add(duenio);
    }

    public void eliminarDuenio(Persona duenio) {
        duenios.remove(duenio);
    }

    public void alquilar(Persona inquilino) throws UnidadException {
        if (!estaHabitado()) {
            habitar();
            inquilinos = new HashSet<>();
            inquilinos.add(inquilino);
        } else
            throw new UnidadException("La unidad esta ocupada");
    }

    public void agregarInquilino(Persona inquilino) {
        inquilinos.add(inquilino);
    }

    public void eliminarInquilino(Persona inquilino) {
        inquilinos.remove(inquilino);
    }

    public boolean estaHabitado() {
        return habitado.equals("S");
    }

    public void liberar() {
        this.inquilinos = new HashSet<>();
        this.habitado = "N";
    }

    public void habitar() throws UnidadException {
        if (estaHabitado()) {
            throw new UnidadException("La unidad ya esta habitada");
        } else {
            this.habitado = "S";
        }
    }
}
