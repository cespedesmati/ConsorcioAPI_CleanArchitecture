package com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.UnidadException;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUnidadService {

    BaseResponse<List<PersonaDTO>> dueniosPorUnidad(Integer codigo, String piso, String numero) throws Exception;

    BaseResponse<List<PersonaDTO>> inquilinosPorUnidad(Integer codigo, String piso, String numero) throws Exception;

    BaseResponse<Boolean> transferirUnidad(Integer codigo, String piso, String numero, String documento) throws Exception;

    Unidad findUnidad(Integer codigo, String piso, String numero) throws UnidadException;

    BaseResponse<Boolean> agregarDuenioUnidad(Integer codigo, String piso, String numero, String documento) throws Exception;

    BaseResponse<Boolean>  alquilarUnidad(Integer codigo, String piso, String numero, String documento) throws Exception;

    BaseResponse<Boolean> agregarInquilino(Integer codigo, String piso, String numero, String documento) throws Exception;

    BaseResponse<Boolean> liberarUnidad(Integer codigo, String piso, String numero) throws Exception;

    BaseResponse<Boolean> habitarUnidad(Integer codigo, String piso, String numero) throws Exception;

    BaseResponse<UnidadDTO> getUnidad(Integer codigo, String piso, String numero) throws Exception;

    BaseResponse<String> createUnidad(UnidadDTO unidadDTO) throws Exception;

    BaseResponse<Boolean> deleteUnidad(Integer identificador) throws Exception;

    BaseResponse<Boolean> deleteDuenio(Integer identificador, String documento) throws Exception;
    BaseResponse<Boolean> deleteInquilino(Integer identificador, String documento) throws Exception;
}
