package com.example.Activdad.controller;

import com.example.Activdad.Persistence.entity.Conductor;
import com.example.Activdad.Persistence.entity.Vehiculo;
import com.example.Activdad.Services.ConductorService;
import com.example.Activdad.Services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoServicio;
    @GetMapping("/ver")
    public List<Vehiculo> findAll(){
        return vehiculoServicio.findAll();

    }
    @PostMapping("/registrar")
    private Vehiculo save (@RequestBody Vehiculo vehiculo){
        return vehiculoServicio.save(vehiculo);
    }
    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<Vehiculo> delete (@PathVariable Long id ){
      Vehiculo vehiculo =  vehiculoServicio.actualizar(id);
     return ResponseEntity.ok(vehiculo);

    }


}
