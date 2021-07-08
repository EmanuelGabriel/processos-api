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
	 * Método responsável por conversão de entidade em DTO
	 * 
	 * @author emanuel.sousa
	 * @param entity
	 * @return dto
	 */
	public ImageModelResponse entityToDTO(ImageModel entity) {
		return this.modelMapper.map(entity, ImageModelResponse.class);
	}

	/**
	 * Método responsável por conversão de DTO em entidade
	 * 
	 * @author emanuel.sousa
	 * @param dto input
	 * @return entity
	 */
	public ImageModel dtoToEntity(ImageModelInpuRequest dto) {
		return this.modelMapper.map(dto, ImageModel.class);
	}

	/**
	 * Converte um DTO para uma Entidade
	 * 
	 * @author emanuel.sousa
	 * @param dto
	 * @return
	 */
	public ImageModel dtoToEntity(ImageModelResponse dto) {
		return this.modelMapper.map(dto, ImageModel.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param List imageModels
	 * @return List imageModelResponse
	 */
	public List<ImageModelResponse> listEntityToDTO(List<ImageModel> imageModels) {
		return imageModels.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public List<ImageModel> listDtoToEntity(List<ImageModelResponse> listImageModelDto) {
		return listImageModelDto.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

	/**
	 * @author emanuel.sousa
	 * @param pageable
	 * @param pageImageModel
	 * @return
	 */
	public Page<ImageModelResponse> mapEntityPageToDTO(Pageable pageable, Page<ImageModel> pageImageModel) {
		List<ImageModelResponse> listDtos = listEntityToDTO(pageImageModel.getContent());
		return new PageImpl<>(listDtos, pageable, pageImageModel.getTotalElements());
	}

}
