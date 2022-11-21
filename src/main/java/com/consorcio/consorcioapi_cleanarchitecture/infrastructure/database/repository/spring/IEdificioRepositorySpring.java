package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring;

import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.EdificioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEdificioRepositorySpring extends JpaRepository<EdificioEntity, Integer> {
    EdificioEntity findByCodigo(Integer codigo);
}
