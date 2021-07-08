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
	 * Converte uma entidade para um DTO
	 * 
	 * @param Recebe como parâmetro um Objeto do tipo 'Processo'
	 * @return Objeto do tipo 'ProcessoModelResponse'
	 */
	public ProcessoModelResponse entityToDTO(Processo processo) {
		ProcessoModelResponse dto = this.modelMapper.map(processo, ProcessoModelResponse.class);
		dto.setImagensProcesso(this.imageMapper.listEntityToDTO(processo.getImages()));
		return dto;
	}

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
