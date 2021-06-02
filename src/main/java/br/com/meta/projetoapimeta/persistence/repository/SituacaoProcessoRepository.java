package br.com.meta.projetoapimeta.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.persistence.entity.SituacaoProcesso;

@Repository
public interface SituacaoProcessoRepository extends JpaRepository<SituacaoProcesso, Long> {

	SituacaoProcesso findByDescricao(String descricao);
}
