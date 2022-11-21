package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.UnidadEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UnidadEntityMapper {

    private final PersonaEntityMapper personaEntityMapper = new PersonaEntityMapper();

    public UnidadEntity toEntity(Unidad unidad){
        return UnidadEntity.builder()
                .identificador(unidad.getIdentificador())
                .piso(unidad.getPiso())
                .numero(unidad.getNumero())
                .habitado(unidad.getHabitado())
                .duenios(getPersonaEntities(unidad.getDuenios()))
                .inquilinos(getPersonaEntities(unidad.getInquilinos()))
                .build();
    }

    public Unidad toDomain(UnidadEntity unidadEntity){
        return Unidad.builder()
                .identificador(unidadEntity.getIdentificador())
                .piso(unidadEntity.getPiso())
                .numero(unidadEntity.getNumero())
                .habitado(unidadEntity.getHabitado())
                .duenios(getPersona(unidadEntity.getDuenios()))
                .inquilinos(getPersona(unidadEntity.getInquilinos()))
                .build();
    }

    private Set<PersonaEntity> getPersonaEntities(Set<Persona> personaSet) {
        Set<PersonaEntity> personaEntitySet = new HashSet<>();
        for (Persona personaDomain : personaSet) {
            var personaEntity = personaEntityMapper.toEntity(personaDomain);
            personaEntitySet.add(personaEntity);
        }
        return personaEntitySet;
    }

    private Set<Persona> getPersona(Set<PersonaEntity> personaEntitySet) {
        Set<Persona> personaSet = new HashSet<>();
        for (PersonaEntity personaEntity : personaEntitySet) {
            var persona = personaEntityMapper.toDomain(personaEntity);
            personaSet.add(persona);
        }
        return personaSet;
    }
}
