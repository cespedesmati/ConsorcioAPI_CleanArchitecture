package com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonaService {

    BaseResponse<List<PersonaDTO>> getAllPersonas() throws Exception;

    BaseResponse<PersonaDTO> findPersonaToDto(String documento) throws Exception;

    Persona findPersona(String documento) throws PersonaException;

    BaseResponse<String> createPersona(PersonaDTO personaDTO) throws Exception;

    void savePersona(Persona persona);

    BaseResponse<String> deletePersona(String documento) throws Exception;

    BaseResponse<String> updatePersona(PersonaDTO personaDTO) throws Exception;
}
