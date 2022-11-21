package com.consorcio.consorcioapi_cleanarchitecture.API.controllers;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IUnidadService;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag( name = "UNIDADES", description = "API para Unidades" )
@RequestMapping( "/api/unidad" )
public class UnidadController {
    private IUnidadService unidadService;

    @Autowired
    public UnidadController(IUnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @GetMapping( "/duenios" )
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> dueniosPorUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.dueniosPorUnidad(codigo, piso, numero));
    }

    @GetMapping( "/inquilinos" )
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> inquilinosPorUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.inquilinosPorUnidad(codigo, piso, numero));
    }

    @PutMapping( "/transferir" )
    public ResponseEntity<BaseResponse<Boolean>> transferirUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.transferirUnidad(codigo, piso, numero, documento));
    }

    @PutMapping( "/agregar/duenio" )
    public ResponseEntity<BaseResponse<Boolean>> agregarDuenioUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.agregarDuenioUnidad(codigo, piso, numero, documento));
    }

    @PutMapping( "/alquilar" )
    public ResponseEntity<BaseResponse<Boolean>> alquilarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.alquilarUnidad(codigo, piso, numero, documento));
    }

    @PutMapping( "/agregar/inquilino" )
    public ResponseEntity<BaseResponse<Boolean>> agregarInquilinoUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.agregarInquilino(codigo, piso, numero, documento));
    }

    @PutMapping( "/liberar" )
    public ResponseEntity<BaseResponse<Boolean>> liberarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.liberarUnidad(codigo, piso, numero));
    }

    @PutMapping( "/habitar" )
    public ResponseEntity<BaseResponse<Boolean>> habitarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.habitarUnidad(codigo, piso, numero));
    }

    @GetMapping( "/unidad" )
    public ResponseEntity<BaseResponse<UnidadDTO>> getUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.getUnidad(codigo, piso, numero));
    }


    @RequestMapping( value = "/crear", method = RequestMethod.POST )
    public ResponseEntity<BaseResponse<String>> createUnidad(UnidadDTO unidadDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.createUnidad(unidadDTO));
    }

    @DeleteMapping( "/{identificador}" )
    public ResponseEntity<BaseResponse<Boolean>> deleteUnidad(@PathVariable( "identificador" ) Integer identificador) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.deleteUnidad(identificador));
    }


    @DeleteMapping( "/{identificador}/inquilino/{documento}" )
    public ResponseEntity<BaseResponse<Boolean>> deleteInquilino(@PathVariable Integer identificador, @PathVariable String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.deleteInquilino(identificador, documento));
    }

    @DeleteMapping( "/{identificador}/duenio/{documento}" )
    public ResponseEntity<BaseResponse<Boolean>> deleteDuenio(@PathVariable Integer identificador, @PathVariable String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(unidadService.deleteDuenio(identificador, documento));
    }
}
