package com.consorcio.consorcioapi_cleanarchitecture.application.service;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IPersonaRepository;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IPersonaService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper.IMapper;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper.PersonaMapper;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonaService implements IPersonaService {

    private final IPersonaRepository personaRepository;
    private IMapper<Persona,PersonaDTO> mapper;

    @Override
    public List<PersonaDTO> getAllPersonas() {
        List<Persona> personaEntities = personaRepository.findAll();
        return personaEntities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PersonaDTO findPersona(String documento) {
        Persona persona = personaRepository.findByDocumento(documento);
        return mapper.toDTO(persona);
    }

    @Override
    public void createPersona(PersonaDTO personaDTO) throws PersonaException {
        try {
            Persona persona = Persona.builder()
                    .documento(personaDTO.getDocumento())
                    .nombre(personaDTO.getNombre())
                    .contrasenia(personaDTO.getContrasenia())
                    .admin(personaDTO.getAdmin() != null && personaDTO.getAdmin())
                    .build();
            personaRepository.save(persona);
        }catch (Exception e){
            throw  new PersonaException(e.getMessage());
        }
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        personaRepository.delete(persona);
    }
}
