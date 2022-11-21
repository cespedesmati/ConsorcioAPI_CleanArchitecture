package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring;

import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepositorySpring extends JpaRepository<PersonaEntity, String> {
    PersonaEntity findByDocumento(String documento);
}
