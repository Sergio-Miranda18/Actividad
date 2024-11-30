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
    @Autowired
    private ConductorRepository conductorRepository;
    public Vehiculo asignarConductor(Long vehiculoId, Long conductorId) {
        // Buscar el vehículo
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + vehiculoId));

        // Verificar si el vehículo ya tiene un conductor asignado
        if (vehiculo.getConductor() != null) {
            throw new RuntimeException("El vehículo ya tiene un conductor asignado.");
        }

        // Buscar el conductor
        Conductor conductor = conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + conductorId));

        // Asignar el conductor al vehículo
        vehiculo.setConductor(conductor);

        System.out.println(vehiculo);
        System.out.println(conductor);
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo save(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return vehiculo;
    }

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();

    }
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) {
        // Busca el vehículo existente por ID
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));

        // Actualiza los campos con los nuevos valores
        vehiculo.setPlaca(vehiculoActualizado.getPlaca());
        vehiculo.setEstado(vehiculoActualizado.isEstado());
        vehiculo.setTipo(vehiculoActualizado.getTipo());
        vehiculo.setConductor(vehiculoActualizado.getConductor());

        // Guarda los cambios en la base de datos
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo actualizar(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con id: " + id));
        vehiculo.setEstado(false);
        return vehiculoRepository.save(vehiculo);
    }
}
