package com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonaService {
    List<PersonaDTO> getAllPersonas();

    PersonaDTO findPersona(String documento);

    void createPersona(PersonaDTO personaDTO) throws PersonaException;

    void savePersona(Persona persona);

    void deletePersona(Persona persona);

}
