package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository;

import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IPersonaRepository;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper.PersonaEntityMapper;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring.IPersonaRepositorySpring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PersonaRepository implements IPersonaRepository {

    private final IPersonaRepositorySpring personaRepositorySpring;
    private final PersonaEntityMapper personaEntityMapper;

    @Override
    public List<Persona> findAll() {
        List<PersonaEntity> personaEntities = personaRepositorySpring.findAll();
        return personaEntities.stream().map(personaEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Persona findByDocumento(String documento) {
        return personaEntityMapper.toDomain(personaRepositorySpring.findByDocumento(documento));
    }

    @Override
    public Persona save(Persona persona) {
        var personaToSave = personaRepositorySpring.save(personaEntityMapper.toEntity(persona));
        return personaEntityMapper.toDomain(personaToSave);
    }

    @Override
    @Transactional
    public void delete(Persona persona) {
        personaRepositorySpring.delete(personaEntityMapper.toEntity(persona));

    }
}
