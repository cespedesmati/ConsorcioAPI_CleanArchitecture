package com.consorcio.consorcioapi_cleanarchitecture.application.repository;

import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonaRepository {
    List<Persona> findAll();
    Persona findByDocumento(String documento);
    Persona save(Persona persona);
    void delete(Persona persona);

}
