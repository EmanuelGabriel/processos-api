package br.com.meta.projetoapimeta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.meta.projetoapimeta.domain.dtos.response.UnidadeGestoraModelResponse;
import br.com.meta.projetoapimeta.domain.entity.UnidadeGestora;
import br.com.meta.projetoapimeta.domain.mapper.UnidadeGestoraMapper;
import br.com.meta.projetoapimeta.domain.repository.UnidadeGestoraRepository;
import br.com.meta.projetoapimeta.services.exception.EntityNaoEncontradaException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnidadeGestoraService {

	@Autowired
	private UnidadeGestoraRepository unidadeGestoraRepository;

	@Autowired
	private UnidadeGestoraMapper gestoraMapper;

	public Page<UnidadeGestoraModelResponse> getAll(Pageable pageable) {
		log.info("Busca todas as unidades gestoras paginadas {}", pageable);
		Page<UnidadeGestora> pageUnidadeGestora = this.unidadeGestoraRepository.findAll(pageable);
		return this.gestoraMapper.mapEntityPageToDTO(pageable, pageUnidadeGestora);
	}

	public UnidadeGestoraModelResponse buscarPorID(Long idUnidadeGestora) {
		log.info("Busca uma unidade gestora por seu ID {}", idUnidadeGestora);
		Optional<UnidadeGestora> unidadeGestoraOpt = this.unidadeGestoraRepository.findById(idUnidadeGestora);
		if (!unidadeGestoraOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Unidade Gestora de ID não encontrado");
		}
		return this.gestoraMapper.entityToDTO(unidadeGestoraOpt.get());
	}

}
