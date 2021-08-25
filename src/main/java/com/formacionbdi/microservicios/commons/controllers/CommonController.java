package com.formacionbdi.microservicios.commons.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.formacionbdi.microservicios.commons.services.CommonService;

import java.util.Optional;

@RestController
public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity<?>listar(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> alumno = service.findById(id);
        if(alumno.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno.get());
    }

    @PostMapping()
    public ResponseEntity<?> crear (@RequestBody E entity){
        E alumnodb= service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnodb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
