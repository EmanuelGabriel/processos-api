package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.request.ImageModelInpuRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.ImageModelResponse;
import br.com.meta.projetoapimeta.domain.entity.ImageModel;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImageModelMapper {

	private ModelMapper modelMapper;

	/**
	 * Converte um objeto do tipo 'ImageModel' para um DTO
	 * 
	 * @param imageModel
	 * @return
	 */
	public ImageModelResponse entityToDTO(ImageModel imageModel) {
		return this.modelMapper.map(imageModel, ImageModelResponse.class);
	}

	public ImageModel dtoToEntity(ImageModelInpuRequest dto) {
		return this.modelMapper.map(dto, ImageModel.class);
	}

	public ImageModel dtoToEntity(ImageModelResponse dto) {
		return this.modelMapper.map(dto, ImageModel.class);
	}

	public List<ImageModelResponse> listEntityToDTO(List<ImageModel> imageModels) {
		return imageModels.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public Page<ImageModelResponse> mapEntityPageToDTO(Pageable pageable, Page<ImageModel> pageImageModel) {
		List<ImageModelResponse> listDtos = listEntityToDTO(pageImageModel.getContent());
		return new PageImpl<>(listDtos, pageable, pageImageModel.getTotalElements());
	}

}
