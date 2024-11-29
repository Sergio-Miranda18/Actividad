package com.example.Activdad.Persistence.Repository;

import com.example.Activdad.Persistence.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
