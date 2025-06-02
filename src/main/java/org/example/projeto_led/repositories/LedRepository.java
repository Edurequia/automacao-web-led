package org.example.projeto_led.repositories;

import org.example.projeto_led.entities.Led;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedRepository extends JpaRepository<Led, Long> {
}
