package br.com.meta.projetoapimeta.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

	Document findByNomeDocumentoContainingIgnoreCase(String nomeDocumento);

	List<Document> findByDataCadastroBetween(LocalDate dataCadastroInicio, LocalDate dataCadastroFinal);

}
