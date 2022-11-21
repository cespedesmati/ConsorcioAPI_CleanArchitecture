package com.consorcio.consorcioapi_cleanarchitecture.application.service;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IPersonaRepository;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IPersonaService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper.IMapper;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.ReplyMessage;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonaService implements IPersonaService {

    private final IPersonaRepository personaRepository;
    private IMapper<Persona, PersonaDTO> mapper;

    @Override
    public BaseResponse<List<PersonaDTO>> getAllPersonas() throws Exception {
        try {
            List<Persona> personaEntities = personaRepository.findAll();

            var response = new BaseResponse<List<PersonaDTO>>();
            response.IsSucces = true;
            response.DataResponse = personaEntities.stream()
                    .map(mapper::toDTO).collect(Collectors.toList());
            response.Message = ReplyMessage.MESSAGE_QUERY;
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<PersonaDTO> findPersonaToDto(String documento) throws Exception {
        try {
            Persona persona = findPersona(documento);
            var response = new BaseResponse<PersonaDTO>();
            response.IsSucces = true;
            response.DataResponse = mapper.toDTO(persona);
            response.Message = ReplyMessage.MESSAGE_QUERY;
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Persona findPersona(String documento) throws PersonaException {
        Persona persona = personaRepository.findByDocumento(documento);
        if (persona == null) {
            throw new PersonaException(ReplyMessage.MESSAGE_QUERY_EMPTY);
        }
        return persona;

    }

    @Override
    public BaseResponse<String> createPersona(PersonaDTO personaDTO) throws Exception {
        try {
            Persona persona = Persona.builder()
                    .documento(personaDTO.getDocumento())
                    .nombre(personaDTO.getNombre())
                    .contrasenia(personaDTO.getContrasenia())
                    .admin(personaDTO.getAdmin() != null && personaDTO.getAdmin())
                    .build();
            personaRepository.save(persona);

            var response = new BaseResponse<String>();
            response.IsSucces = true;
            response.Message = ReplyMessage.MESSAGE_SAVE;
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public BaseResponse<String> deletePersona(String documento) throws Exception {
        try {
            var response = new BaseResponse<String>();
            Persona persona = findPersona(documento);
            personaRepository.delete(persona);
            response.IsSucces = true;
            response.Message = ReplyMessage.MESSAGE_DELETE;
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public BaseResponse<String> updatePersona(PersonaDTO personaDTO) throws Exception {
        try {
            Persona persona = findPersona(personaDTO.getDocumento());
            var nombre = personaDTO.getNombre() == null ? persona.getNombre() : personaDTO.getNombre();
            var contrasenia = personaDTO.getContrasenia() == null ? persona.getContrasenia() : personaDTO.getContrasenia();
            var admin = personaDTO.getAdmin() == null ? persona.getAdmin() : personaDTO.getAdmin();
            persona.setNombre(nombre);
            persona.setContrasenia(contrasenia);
            persona.setAdmin(admin);
            personaRepository.save(persona);

            var response = new BaseResponse<String>();
            response.IsSucces = true;
            response.Message= ReplyMessage.MESSAGE_UPDATE;
            return response;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
