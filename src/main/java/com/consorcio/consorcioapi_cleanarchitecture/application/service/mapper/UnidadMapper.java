package com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnidadMapper implements IMapper<Unidad, UnidadDTO> {
    @Override
    public Unidad toModel(UnidadDTO unidadDTO) {
        return Unidad.builder()
                .piso(unidadDTO.getPiso())
                .numero(unidadDTO.getNumero())
                .habitado(unidadDTO.getHabitado())
                .build();

    }

    @Override
    public UnidadDTO toDTO(Unidad model) {

        List<String>
                documentoInquilinos =
                model.getInquilinos().stream().map(Persona::getDocumento).collect(Collectors.toList());
        List<String>
                documentoDuenios =
                model.getDuenios().stream().map(Persona::getDocumento).collect(Collectors.toList());
        return UnidadDTO.builder()
                .identificador(model.getIdentificador())
                .habitado(model.getHabitado())
                .numero(model.getNumero())
                .piso(model.getPiso())
                .documentoDuenios(documentoDuenios)
                .documentoInquilinos(documentoInquilinos)
                .build();
    }
}
