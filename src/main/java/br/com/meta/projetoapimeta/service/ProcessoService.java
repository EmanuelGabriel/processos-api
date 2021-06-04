package br.com.meta.projetoapimeta.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.meta.projetoapimeta.domain.mapper.ProcessoMapper;
import br.com.meta.projetoapimeta.model.request.ProcessoModelInputRequest;
import br.com.meta.projetoapimeta.model.response.ProcessoModelResponse;
import br.com.meta.projetoapimeta.persistence.entity.Processo;
import br.com.meta.projetoapimeta.persistence.entity.SituacaoProcesso;
import br.com.meta.projetoapimeta.persistence.entity.enums.FaseProcesso;
import br.com.meta.projetoapimeta.persistence.repository.ProcessoRepository;
import br.com.meta.projetoapimeta.persistence.repository.SituacaoProcessoRepository;
import br.com.meta.projetoapimeta.service.exception.EntityNaoEncontradaException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessoService {

	private static final String MSG_IMPOSSIVEL_REMOVER = "O processo não pode ser removido, pois está em uso";
	private ProcessoRepository processoRepository;
	private SituacaoProcessoRepository situacaoProcessoRepository;
	private ProcessoMapper processoMapper;

	@Transactional
	public ProcessoModelResponse criar(ProcessoModelInputRequest request) {
		log.info("Criar um processo {}", request);

		Optional<SituacaoProcesso> situacaoProcessoId = this.situacaoProcessoRepository
				.findById(request.getSituacaoProcesso().getId());
		if (!situacaoProcessoId.isPresent()) {
			throw new EntityNaoEncontradaException("Situação do processo não encontrada");
		}

		Processo processo = new Processo();
		processo.setCpf(request.getCpf());
		processo.setDataInicioProcesso(LocalDateTime.now());
		processo.setDataPrazoEstimado(LocalDateTime.now().plusDays(30));
		processo.setFaseInicial(FaseProcesso.INICIAL);
		processo.setNumeroProcessoSei(request.getNumeroProcessoSei());
		processo.setNumeroProtocolo(request.getNumeroProtocolo());
		processo.setDataAtualizacaoProcesso(LocalDateTime.now());
		processo.setSituacaoProcesso(situacaoProcessoId.get());

		return this.processoMapper.entityToDTO(this.processoRepository.saveAndFlush(processo));
	}

	public ProcessoModelResponse buscarPorId(Long idProcesso) {
		log.info("Busca processo por ID {}", idProcesso);
		Optional<Processo> processoOpt = this.processoRepository.findById(idProcesso);
		if (!processoOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Processo de ID não encontrado");
		}
		return this.processoMapper.entityToDTO(processoOpt.get());
	}

	public Page<ProcessoModelResponse> getAll() {
		Pageable pageable = PageRequest.of(0, 10,
				Sort.by("id").ascending().and(Sort.by("dataInicioProcesso").ascending()));
		Page<Processo> pageProcesso = this.processoRepository.findAll(pageable);
		log.info(
				"Busca todos os processos com dados paginados -  total de elementos: {} - Quantidade {}: - Número de elementos: {} - size: {} - TotalPages: {}",
				pageProcesso.getTotalElements(), pageProcesso.getNumber(), pageProcesso.getNumberOfElements(),
				pageProcesso.getSize(), pageProcesso.getTotalPages());
		return this.processoMapper.mapEntityPageToDTO(pageable, pageProcesso);
	}

	@Transactional
	public void remover(Long idProcesso) {
		log.info("Remover processo de ID", idProcesso);
		try {

			buscarPorId(idProcesso);
			this.processoRepository.deleteById(idProcesso);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(MSG_IMPOSSIVEL_REMOVER);
		}
	}

}
