package com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper implements IMapper<Persona,PersonaDTO>{

    public Persona toModel(PersonaDTO personaDTO){
        return Persona.builder()
                .documento(personaDTO.getDocumento())
                .nombre(personaDTO.getNombre())
                .contrasenia(personaDTO.getContrasenia())
                .admin(personaDTO.getAdmin() != null && personaDTO.getAdmin())
                .build();
    }


    public PersonaDTO toDTO(Persona persona){
        return PersonaDTO.builder()
                .documento(persona.getDocumento())
                .nombre(persona.getNombre())
                .build();
    }
}
