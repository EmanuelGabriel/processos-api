package br.com.meta.projetoapimeta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	Processo findByNumeroProtocolo(Integer numeroProtocolo);

}
