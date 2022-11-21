package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository;

import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IUnidadRepository;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.UnidadEntity;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.mapper.UnidadEntityMapper;
import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring.IUnidadRepositorySpring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UnidadRepository implements IUnidadRepository {

    private final IUnidadRepositorySpring unidadRepositorySpring;
    private final UnidadEntityMapper unidadEntityMapper;

    @Override
    public Unidad findyByIdentificador(Integer identificador) {
        return unidadEntityMapper.toDomain(unidadRepositorySpring.findByIdentificador(identificador));
    }

    @Override
    public List<Unidad> findAll() {
        List<UnidadEntity> unidadEntities = unidadRepositorySpring.findAll();
        return unidadEntities.stream().map(unidadEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(Unidad unidad) {
        unidadRepositorySpring.delete(unidadEntityMapper.toEntity(unidad));
    }

    @Override
    public Unidad findUnidadByCodigoEdificioAndPisoAndNumero(Integer codigoEdificio, String piso, String numero) {
        return unidadEntityMapper
                .toDomain(unidadRepositorySpring
                        .findUnidadByCodigoEdificioCodigoAndPisoAndNumero(codigoEdificio,piso,numero));
    }

    @Override
    public void save(Unidad unidad) {
        UnidadEntity unidadEntity =  unidadEntityMapper.toEntity(unidad);
        unidadRepositorySpring.save(unidadEntity);
    }

    @Override
    public Boolean existsUnidad(Integer codigoEdificio, String piso, String numero) {
        return unidadRepositorySpring.existsByCodigoEdificioCodigoAndPisoAndNumero(codigoEdificio,piso,numero);
    }
}
