package com.example.Activdad.Persistence.Repository;


import com.example.Activdad.Persistence.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

}
