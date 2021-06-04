package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.model.response.ProcessoModelResponse;
import br.com.meta.projetoapimeta.persistence.entity.Processo;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProcessoMapper {

	private ModelMapper modelMapper;

	/**
	 * Converte uma entidade para um DTO
	 * 
	 * @param processo
	 * @return
	 */
	public ProcessoModelResponse entityToDTO(Processo processo) {
		return this.modelMapper.map(processo, ProcessoModelResponse.class);
	}

	public Processo dtoToEntity(ProcessoModelResponse dto) {
		return this.modelMapper.map(dto, Processo.class);
	}

	public List<ProcessoModelResponse> listEntityToDTO(List<Processo> processos) {
		return processos.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public List<ProcessoModelResponse> mapPageDTO(Page<Processo> processos) {
		return processos.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public Page<ProcessoModelResponse> mapEntityPageToDTO(Pageable pageable, Page<Processo> pageProcesso) {
		List<ProcessoModelResponse> dtos = listEntityToDTO(pageProcesso.getContent());
		return new PageImpl<>(dtos, pageable, pageProcesso.getTotalElements());
	}
}
