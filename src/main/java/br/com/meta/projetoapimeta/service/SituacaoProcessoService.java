package br.com.meta.projetoapimeta.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meta.projetoapimeta.domain.mapper.SituacaoProcessoMapper;
import br.com.meta.projetoapimeta.model.request.SituacaoProcessoModelInputRequest;
import br.com.meta.projetoapimeta.model.response.SituacaoProcessoModelResponse;
import br.com.meta.projetoapimeta.persistence.entity.SituacaoProcesso;
import br.com.meta.projetoapimeta.persistence.repository.SituacaoProcessoRepository;
import br.com.meta.projetoapimeta.service.exception.EntityNaoEncontradaException;
import br.com.meta.projetoapimeta.service.exception.RegraNegocioException;
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

	public List<SituacaoProcessoModelResponse> getAll() {
		log.info("Busca todas as situações dos processos");
		return this.situacaoProcessoMapper.listEntityToDTO(this.situacaoProcessoRepository.findAll());
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
