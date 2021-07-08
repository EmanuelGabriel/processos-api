package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.response.ProcessoModelResponse;
import br.com.meta.projetoapimeta.domain.entity.Processo;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProcessoMapper {

	private ModelMapper modelMapper;

	@Autowired
	private ImageModelMapper imageMapper;

	/**
	 * Método responsável por conversão de entidade em DTO
	 * 
	 * @author emanuel.sousa
	 * @param entity
	 * @return DTO
	 */
	public ProcessoModelResponse entityToDTO(Processo entity) {
		ProcessoModelResponse dto = this.modelMapper.map(entity, ProcessoModelResponse.class);
		dto.setImagensProcesso(this.imageMapper.listEntityToDTO(entity.getImages()));
		return dto;
	}

	/**
	 * Método responsável por conversão de DTO em entidade
	 * @author emanuel.sousa
	 * @param dto
	 * @return entity
	 */
	public Processo dtoToEntity(ProcessoModelResponse dto) {
		Processo processo = this.modelMapper.map(dto, Processo.class);
		processo.setImages(this.imageMapper.listDtoToEntity(dto.getImagensProcesso()));
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
