package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository;

import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IEdificioRepository;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.EdificioEntity;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper.EdificioEntityMapper;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring.IEdificioRepositorySpring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EdificioRepository implements IEdificioRepository {
    private final IEdificioRepositorySpring edificioRepositorySpring;
    private final EdificioEntityMapper edificioEntityMapper;

    @Override
    public Edificio findByCodigo(Integer codigo) {
        return edificioEntityMapper.toDomain(edificioRepositorySpring.findByCodigo(codigo));
    }

    @Override
    public List<Edificio> findAll() {
        List<EdificioEntity> edificioEntities = edificioRepositorySpring.findAll();
        return edificioEntities.stream().map(edificioEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Edificio save(Edificio edificio) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Edificio edificio) {
        edificioRepositorySpring.delete(edificioEntityMapper.toEntity(edificio));
    }
}
