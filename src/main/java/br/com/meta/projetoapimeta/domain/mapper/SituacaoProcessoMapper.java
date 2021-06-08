package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.request.SituacaoProcessoModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.SituacaoProcessoModelResponse;
import br.com.meta.projetoapimeta.domain.entity.SituacaoProcesso;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SituacaoProcessoMapper {

	private ModelMapper modelMapper;

	/**
	 * Converte um objeto do tipo 'Situação Processo' para um DTO
	 * 
	 * @param situacaoProcesso
	 * @return
	 */
	public SituacaoProcessoModelResponse entityToDTO(SituacaoProcesso situacaoProcesso) {
		return this.modelMapper.map(situacaoProcesso, SituacaoProcessoModelResponse.class);
	}

	public SituacaoProcesso dtoToEntity(SituacaoProcessoModelInputRequest dto) {
		return this.modelMapper.map(dto, SituacaoProcesso.class);
	}

	public SituacaoProcesso dtoToEntity(SituacaoProcessoModelResponse dto) {
		return this.modelMapper.map(dto, SituacaoProcesso.class);
	}

	public List<SituacaoProcessoModelResponse> listEntityToDTO(List<SituacaoProcesso> situacoes) {
		return situacoes.stream().map(this::entityToDTO).collect(Collectors.toList());
	}
	
	public Page<SituacaoProcessoModelResponse> mapEntityPageToDTO(Pageable pageable, Page<SituacaoProcesso> pageSituacao){
		List<SituacaoProcessoModelResponse> listDtos = listEntityToDTO(pageSituacao.getContent());
		return new PageImpl<>(listDtos, pageable, pageSituacao.getTotalElements());
	}

}
