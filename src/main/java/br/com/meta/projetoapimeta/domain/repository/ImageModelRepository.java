package br.com.meta.projetoapimeta.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.ImageModel;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {

	List<ImageModel> findByNomeIgnoreCaseContaining(String nome);

	@Query("SELECT im FROM ImageModel im WHERE im.id = ?1")
	List<ImageModel> buscarImagePorID(Long idImagem);

	Optional<ImageModel> findById(Long idImagem);

	List<ImageModel> findByType(String type);

}
