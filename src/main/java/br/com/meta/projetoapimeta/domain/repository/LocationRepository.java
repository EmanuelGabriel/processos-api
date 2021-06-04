package br.com.meta.projetoapimeta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
