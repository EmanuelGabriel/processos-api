package br.com.meta.projetoapimeta.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.meta.projetoapimeta.domain.dtos.request.SituacaoProcessoModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.SituacaoProcessoModelResponse;
import br.com.meta.projetoapimeta.domain.entity.SituacaoProcesso;
import br.com.meta.projetoapimeta.domain.mapper.SituacaoProcessoMapper;
import br.com.meta.projetoapimeta.domain.repository.SituacaoProcessoRepository;
import br.com.meta.projetoapimeta.services.exception.EntityNaoEncontradaException;
import br.com.meta.projetoapimeta.services.exception.RegraNegocioException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SituacaoProcessoService {

	private static final String MSG_IMPOSSIVEL_REMOVER = "A situação do processo não pode ser removido, pois está em uso";
	private SituacaoProcessoRepository situacaoProcessoRepository;
	private SituacaoProcessoMapper situacaoProcessoMapper;

	@Transactional
	public SituacaoProcessoModelResponse criar(SituacaoProcessoModelInputRequest request) {
		log.info("Criando uma situação do processo {}", request);

		SituacaoProcesso situacaoExistente = this.situacaoProcessoRepository.findByDescricao(request.getDescricao());
		if (situacaoExistente != null && !situacaoExistente.equals(request)) {
			throw new RegraNegocioException("Já existe uma situação do processo registrado com este nome");
		}

		SituacaoProcesso situacaoProcesso = this.situacaoProcessoMapper.dtoToEntity(request);
		return this.situacaoProcessoMapper.entityToDTO(this.situacaoProcessoRepository.saveAndFlush(situacaoProcesso));

	}
	
	public Page<SituacaoProcessoModelResponse> getAll(Pageable pageable){
		// Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
		Page<SituacaoProcesso> pageSituacaoProcesso = this.situacaoProcessoRepository.findAll(pageable);
		log.info("Busca todas as situações de um processo com paginação - total de elementos {} - total de páginas {}", pageSituacaoProcesso.getTotalElements(), pageSituacaoProcesso.getTotalPages());
		return this.situacaoProcessoMapper.mapEntityPageToDTO(pageable, pageSituacaoProcesso);
	}

	public SituacaoProcessoModelResponse buscarPorId(Long idSituacao) {
		log.info("Busca uma situação por ID {}", idSituacao);
		Optional<SituacaoProcesso> situacaoProcessoOpt = this.situacaoProcessoRepository.findById(idSituacao);
		if (!situacaoProcessoOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Processo de ID não encontrado");
		}
		return this.situacaoProcessoMapper.entityToDTO(situacaoProcessoOpt.get());
	}

	@Transactional
	public void remover(Long idSituacao) {
		try {

			buscarPorId(idSituacao);
			this.situacaoProcessoRepository.deleteById(idSituacao);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(MSG_IMPOSSIVEL_REMOVER);
		}
	}

}
