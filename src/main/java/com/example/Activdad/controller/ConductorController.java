package com.example.Activdad.controller;


import com.example.Activdad.Persistence.entity.Conductor;
import com.example.Activdad.Persistence.entity.Vehiculo;
import com.example.Activdad.Services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {
    @Autowired
    private ConductorService conductorServicio;
    @GetMapping ("/ver")
    public List <Conductor> findAll(){
        return conductorServicio.findAll();

    }
    @PostMapping ("/registrar")
    private Conductor save (@RequestBody Conductor conductor){
       System.out.println(conductor);
        return conductorServicio.save(conductor);
    }

    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<Conductor> delete (@PathVariable Long id ){
        Conductor conductor =  conductorServicio.actualizar(id);
        return ResponseEntity.ok(conductor);




    }
}
