package com.consorcio.consorcioapi_cleanarchitecture.application.repository;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUnidadRepository {
    Unidad findyByIdentificador(Integer identificador);
    List<Unidad> findAll();
    void delete(Unidad unidad);
    Unidad findUnidadByCodigoEdificioAndPisoAndNumero(Integer codigoEdificio, String piso, String numero);

    void save(Unidad unidad);

    Boolean existsUnidad(Integer codigoEdificio, String piso, String numero);
}
