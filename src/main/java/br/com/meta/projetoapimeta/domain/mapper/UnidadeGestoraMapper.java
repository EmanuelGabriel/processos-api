package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.response.UnidadeGestoraModelResponse;
import br.com.meta.projetoapimeta.domain.entity.UnidadeGestora;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UnidadeGestoraMapper {

	private ModelMapper modelMapper;

	public UnidadeGestoraModelResponse entityToDTO(UnidadeGestora unidadeGestora) {
		return this.modelMapper.map(unidadeGestora, UnidadeGestoraModelResponse.class);
	}

	public List<UnidadeGestoraModelResponse> listEntityToDTO(List<UnidadeGestora> ugs) {
		return ugs.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public Page<UnidadeGestoraModelResponse> mapEntityPageToDTO(Pageable pageable,
			Page<UnidadeGestora> pageUnidadeGestora) {
		List<UnidadeGestoraModelResponse> listDtos = listEntityToDTO(pageUnidadeGestora.getContent());
		return new PageImpl<>(listDtos, pageable, pageUnidadeGestora.getTotalElements());
	}

}
