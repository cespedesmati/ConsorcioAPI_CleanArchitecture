package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.repository.spring;


import com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnidadRepositorySpring extends JpaRepository<UnidadEntity,Integer>{
    UnidadEntity findByIdentificador(Integer identificador);
    UnidadEntity findUnidadByCodigoEdificioCodigoAndPisoAndNumero(Integer codigo, String piso, String numero);
    Boolean existsByCodigoEdificioCodigoAndPisoAndNumero(Integer codigoEdificio, String piso, String numero);
}
