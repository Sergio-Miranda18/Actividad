package com.example.Activdad.Persistence.Repository;


import com.example.Activdad.Persistence.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

}
