package com.example.Activdad.Services;

import com.example.Activdad.Persistence.Repository.ConductorRepository;
import com.example.Activdad.Persistence.entity.Conductor;
import com.example.Activdad.Persistence.entity.Vehiculo;
import com.example.Activdad.domain.ConductorDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;

    public Conductor save(Conductor conductor) {
        conductorRepository.save(conductor);
        return conductor;
    }

    public List<Conductor> findAll() {
        return conductorRepository.findAll();



    }

    
    public Conductor actualizar(Long id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con id: " + id));
        conductor.setEstado(false);
        return conductorRepository.save(conductor);
    }
}






