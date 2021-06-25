package br.com.meta.projetoapimeta.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.ImageModel;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {

	Optional<ImageModel> findByNome(String nome);

}
