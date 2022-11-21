package com.consorcio.consorcioapi_cleanarchitecture.application.repository;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEdificioRepository {
    Edificio findByCodigo(Integer codigo);
    List<Edificio> findAll();
    Edificio save(Edificio edificio);
    void delete(Edificio edificio);
}
