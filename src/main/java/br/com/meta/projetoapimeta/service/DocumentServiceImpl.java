package br.com.meta.projetoapimeta.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.meta.projetoapimeta.domain.mapper.DocumentMapper;
import br.com.meta.projetoapimeta.model.request.DocumentModelInputRequest;
import br.com.meta.projetoapimeta.model.response.DocumentModelResponse;
import br.com.meta.projetoapimeta.persistence.entity.Document;
import br.com.meta.projetoapimeta.persistence.entity.enums.TipoSituacao;
import br.com.meta.projetoapimeta.persistence.repository.DocumentRepository;
import br.com.meta.projetoapimeta.persistence.repository.specification.DocumentSpecifications;
import br.com.meta.projetoapimeta.service.exception.RegraNegocioException;
import br.com.meta.projetoapimeta.service.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	private final Mapper<DocumentModelInputRequest, Document> documentRequestMapper;
	private final Mapper<Document, DocumentModelResponse> documentResponseMapper;
	private final DocumentMapper documentMapper;

	public DocumentServiceImpl(DocumentRepository documentRepository,
			Mapper<DocumentModelInputRequest, Document> documentRequestMapper,
			Mapper<Document, DocumentModelResponse> documentResponseMapper, DocumentMapper documentMapper) {
		this.documentRepository = documentRepository;
		this.documentRequestMapper = documentRequestMapper;
		this.documentResponseMapper = documentResponseMapper;
		this.documentMapper = documentMapper;
	}

	@Override
	public DocumentModelResponse criar(DocumentModelInputRequest request) {
		log.info("Criando um Documento {}", request);
		Assert.notNull(request, "Request inválida");

		Document document = this.documentRequestMapper.map(request);
		Document documentExistente = this.documentRepository.findByNomeDocumentoContainingIgnoreCase(request.getNomeDocumento());

		if (documentExistente != null && !documentExistente.equals(request)) {
			throw new RegraNegocioException("Já existe um documento registrado com este nome");
		}

		document.setDataCadastro(LocalDate.now());
		if (request.getDataEstimadaConclusao().isBefore(document.getDataCadastro())) {
			throw new RegraNegocioException(
					"A data estimada para conclusão do documento não pode ser anterior a data atual");
		}

		document.setDataEstimadaConclusao(request.getDataEstimadaConclusao().plusDays(30)); // TODO: usuário tem um
																							// prazo de 30 dias para
																							// conclusão do documento
		document.setSituacao(TipoSituacao.PENDENTE);

		return this.documentRepository.saveAndFlush(document)
				.map((Document input) -> this.documentResponseMapper.map(input));
	}

	@Override
	public Page<DocumentModelResponse> findAll(Pageable pageable) {
		log.info("Busca todos os documents");
		Assert.notNull(pageable, "Página inválida");
		return this.documentRepository.findAll(pageable).map(document -> this.documentResponseMapper.map(document));
	}

	@Override
	public Optional<DocumentModelResponse> buscarPorId(Long idDocumento) {
		log.info("Busca documento por ID {}", idDocumento);
		return this.documentRepository.findById(idDocumento).map(this.documentResponseMapper::map);
	}

	@Override
	public Optional<DocumentModelResponse> buscarPorNumeroProcesso(Integer numeroProcesso) {
		log.info("Busca documento por número do processo {}", numeroProcesso);
		return this.documentRepository.findOne(DocumentSpecifications.getNumeroProcesso(numeroProcesso))
				.map(doc -> this.documentResponseMapper.map(doc));
	}

	@Override
	public Optional<DocumentModelResponse> buscarPorNomeDocumento(String nomeDocumento) {
		log.info("Busca documento por seu nome {}", nomeDocumento);
		Assert.notNull(nomeDocumento, "Não encontrado");
		return this.documentRepository.findOne(DocumentSpecifications.likeNomeDocumento(nomeDocumento))
				.map(doc -> this.documentResponseMapper.map(doc));
	}

	@Override
	public List<DocumentModelResponse> buscarPorDataEstimadaConclusao(LocalDate dataEstimadaConclusaoInicio,
			LocalDate dataEstimadaConclusaoFim) {
		log.info("Busca documento por data estimada de conclusão inicial {} e final {}", dataEstimadaConclusaoInicio,
				dataEstimadaConclusaoFim);
		List<Document> documentos = this.documentRepository.findAll(DocumentSpecifications
				.buscarPorDataEstimadaConclusao(dataEstimadaConclusaoInicio, dataEstimadaConclusaoFim));
		return this.documentMapper.listEntityToDTO(documentos);
	}

	@Override
	public List<DocumentModelResponse> buscarPorDataCadastro(LocalDate dataCadastroInicial,
			LocalDate dataCadastroFinal) {
		log.info("Busca documento por data de cadastro inicial {} e final {}", dataCadastroInicial, dataCadastroFinal);
		List<Document> documentos = this.documentRepository.findByDataCadastroBetween(dataCadastroInicial,
				dataCadastroFinal);
		return this.documentMapper.listEntityToDTO(documentos);
	}

}
