package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonaEntityMapper {

    public PersonaEntity toEntity(Persona persona){
        return PersonaEntity.builder()
                .documento(persona.getDocumento())
                .nombre(persona.getNombre())
                .contrasenia(persona.getContrasenia())
                .admin(persona.getAdmin())
                .build();
    }

    public Persona toDomain(PersonaEntity personaEntity){
        return Persona.builder()
                .documento(personaEntity.getDocumento())
                .nombre(personaEntity.getNombre())
                .contrasenia(personaEntity.getContrasenia())
                .admin(personaEntity.getAdmin())
                .build();
    }
}
