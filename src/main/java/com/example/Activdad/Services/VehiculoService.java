package com.example.Activdad.Services;

import com.example.Activdad.Persistence.Repository.ConductorRepository;
import com.example.Activdad.Persistence.Repository.VehiculoRepository;
import com.example.Activdad.Persistence.entity.Conductor;
import com.example.Activdad.Persistence.entity.Vehiculo;
import com.example.Activdad.domain.VehiculoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;


    public Vehiculo save(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return vehiculo;
    }

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();

    }


    public Vehiculo actualizar(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con id: " + id));
        vehiculo.setEstado(false);
        return vehiculoRepository.save(vehiculo);
    }
}
