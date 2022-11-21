package com.consorcio.consorcioapi_cleanarchitecture.application.service;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.UnidadException;
import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IUnidadRepository;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IEdificioService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IPersonaService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IUnidadService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper.IMapper;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.ReplyMessage;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UnidadService implements IUnidadService {
    private final IUnidadRepository unidadRepository;
    private final IPersonaService personaService;
    private final IEdificioService edificioService;
    private IMapper<Unidad, UnidadDTO> mapperUnidad;
    private IMapper<Persona, PersonaDTO> mapperPersona;

    @Override
    public Unidad findUnidad(Integer codigo, String piso, String numero) throws UnidadException {
        Unidad unidad =  unidadRepository.findUnidadByCodigoEdificioAndPisoAndNumero(codigo,piso,numero);
        if (unidad == null){
            throw new UnidadException(ReplyMessage.MESSAGE_QUERY_EMPTY);
        }
        return unidad;
    }

    @Override
    public BaseResponse<List<PersonaDTO>> dueniosPorUnidad(Integer codigo, String piso, String numero) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            List<Persona> dueniosDomain = new ArrayList<>(unidad.getDuenios());
            List<PersonaDTO> duenios =  dueniosDomain.stream().map(mapperPersona::toDTO).collect(Collectors.toList());
            return new BaseResponse<>(true,duenios, ReplyMessage.MESSAGE_QUERY);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<List<PersonaDTO>> inquilinosPorUnidad(Integer codigo, String piso, String numero) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            List<Persona> inquilinosDomain = new ArrayList<>(unidad.getInquilinos());
            List<PersonaDTO> inquilinos =  inquilinosDomain.stream().map(mapperPersona::toDTO).collect(Collectors.toList());
            return new BaseResponse<>(true,inquilinos, ReplyMessage.MESSAGE_QUERY);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> transferirUnidad(Integer codigo, String piso, String numero, String documento) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            Persona persona = personaService.findPersona(documento);
            unidad.transferir(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public BaseResponse<Boolean> agregarDuenioUnidad(Integer codigo, String piso, String numero, String documento) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            Persona persona = personaService.findPersona(documento);
            unidad.agregarDuenio(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> alquilarUnidad(Integer codigo, String piso, String numero, String documento) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            Persona persona = personaService.findPersona(documento);
            unidad.alquilar(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> agregarInquilino(Integer codigo, String piso, String numero, String documento) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            Persona persona = personaService.findPersona(documento);
            unidad.agregarInquilino(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> liberarUnidad(Integer codigo, String piso, String numero) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            unidad.liberar();
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> habitarUnidad(Integer codigo, String piso, String numero) throws Exception {
        try {
            Unidad unidad = findUnidad(codigo,piso,numero);
            unidad.habitar();
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<UnidadDTO> getUnidad(Integer codigo, String piso, String numero) throws Exception {
        try {
            UnidadDTO unidad = mapperUnidad.toDTO(findUnidad(codigo,piso,numero));
            return new BaseResponse<>(true,unidad, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<String> createUnidad(UnidadDTO unidadDTO) throws Exception {
        try {
            existUnidad(unidadDTO.getCodigoEdificio(),unidadDTO.getPiso(),unidadDTO.getNumero());
            Edificio edificio = edificioService.findEdificio(unidadDTO.getCodigoEdificio());

            Unidad unidad = Unidad.builder()
                    .codigoEdificio(edificio)
                    .piso(unidadDTO.getPiso())
                    .numero(unidadDTO.getNumero())
                    .habitado(unidadDTO.getHabitado())
                    .duenios(new HashSet<>())
                    .inquilinos(new HashSet<>())
                    .build();
            unidadRepository.save(unidad);
            edificio.agregarUnidad(unidad);
            edificioService.save(edificio);
            return new BaseResponse<>(true,unidad.getIdentificador().toString(), ReplyMessage.MESSAGE_UPDATE);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> deleteUnidad(Integer identificador) throws Exception {
        try {
            Unidad unidad = findUnidadByIdentificador(identificador);
            Edificio edificio = edificioService.findEdificio(unidad.getCodigoEdificio().getCodigo());
            edificio.eliminarUnidad(unidad);
            edificioService.save(edificio);
            unidadRepository.delete(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> deleteDuenio(Integer identificador, String documento) throws Exception {
        try {
            Unidad unidad = findUnidadByIdentificador(identificador);
            Persona persona = personaService.findPersona(documento);
            unidad.eliminarDuenio(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> deleteInquilino(Integer identificador, String documento) throws Exception {
        try {
            Unidad unidad = findUnidadByIdentificador(identificador);
            Persona persona = personaService.findPersona(documento);
            unidad.eliminarInquilino(persona);
            unidadRepository.save(unidad);
            return new BaseResponse<>(true,true, ReplyMessage.MESSAGE_UPDATE);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void existUnidad(Integer codigo, String piso, String numero) throws UnidadException {
        Boolean exists =  unidadRepository.existsUnidad(codigo,piso,numero);
        if (!exists){
            throw new UnidadException("La unidad ya existe");
        }
    }

    private Unidad findUnidadByIdentificador(Integer identificador) throws UnidadException {
        Unidad unidad = unidadRepository.findyByIdentificador(identificador);
        if (unidad == null){
            throw new UnidadException("La unidad no existe");
        }
        return unidad;
    }

}

