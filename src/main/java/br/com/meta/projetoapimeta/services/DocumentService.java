package br.com.meta.projetoapimeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meta.projetoapimeta.domain.dtos.request.DocumentModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.DocumentModelResponse;

public interface DocumentService {

	DocumentModelResponse criar(DocumentModelInputRequest request);

	Page<DocumentModelResponse> findAll(Pageable pageable);

	Optional<DocumentModelResponse> buscarPorId(Long idDocumento);

	Optional<DocumentModelResponse> buscarPorNumeroProcesso(Integer numeroProcesso);

	Optional<DocumentModelResponse> buscarPorNomeDocumento(String nomeDocumento);

	List<DocumentModelResponse> buscarPorDataEstimadaConclusao(LocalDate dataEstimadaConclusaoInicio,
			LocalDate dataEstimadaConclusaoFim);

	List<DocumentModelResponse> buscarPorDataCadastro(LocalDate dataCadastroInicial, LocalDate dataCadastroFinal);

}
