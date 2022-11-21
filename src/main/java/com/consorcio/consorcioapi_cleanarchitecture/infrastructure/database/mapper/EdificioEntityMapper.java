package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.EdificioEntity;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EdificioEntityMapper {
    private final UnidadEntityMapper unidadEntityMapper = new UnidadEntityMapper();

    public EdificioEntity toEntity(Edificio edificio){
        return EdificioEntity.builder()
                .codigo(edificio.getCodigo())
                .nombre(edificio.getNombre())
                .direccion(edificio.getDireccion())
                .unidades(edificio.getUnidades().stream().map(unidadEntityMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    public Edificio toDomain(EdificioEntity edificioEntity){
        return Edificio.builder()
                .codigo(edificioEntity.getCodigo())
                .nombre(edificioEntity.getNombre())
                .direccion(edificioEntity.getDireccion())
                .unidades(edificioEntity.getUnidades().stream().map(unidadEntityMapper::toDomain).collect(Collectors.toList()))
                .build();
    }
}
