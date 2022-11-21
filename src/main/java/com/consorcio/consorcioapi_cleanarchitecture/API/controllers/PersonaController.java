package com.consorcio.consorcioapi_cleanarchitecture.API.controllers;


import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IPersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "PERSONAS", description = "API para Personas")
@Slf4j
@RequestMapping("/api/administracion")
public class PersonaController {

    private IPersonaService personaService;

    @Autowired
    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("{documento}")
    public ResponseEntity<PersonaDTO> getPersonaPorDocumento(@PathVariable String documento){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.findPersona(documento));
    }

    @GetMapping()
    public ResponseEntity<List<PersonaDTO>> getPersonas(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.getAllPersonas());
    }

    @PostMapping()
    public ResponseEntity<String> createPersona(@RequestBody PersonaDTO personaDTO) throws PersonaException {
        personaService.createPersona(personaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente");
    }
}

