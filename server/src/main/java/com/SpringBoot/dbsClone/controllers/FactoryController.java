package com.SpringBoot.dbsClone.controllers;

import com.SpringBoot.dbsClone.models.Factory;
import com.SpringBoot.dbsClone.repositories.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/factory")
public class FactoryController {
    @Autowired
    private FactoryRepository factoryRepository;

    @GetMapping()
    ResponseEntity<List<Factory>> getAllFactory() {
        return ResponseEntity.status(HttpStatus.OK).body(factoryRepository.findAll());
    }
}
