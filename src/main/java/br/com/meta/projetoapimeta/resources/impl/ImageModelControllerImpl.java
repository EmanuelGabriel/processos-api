package br.com.meta.projetoapimeta.resources.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.meta.projetoapimeta.domain.dtos.request.ImageModelInpuRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.ImageModelResponse;
import br.com.meta.projetoapimeta.domain.entity.ImageModel;
import br.com.meta.projetoapimeta.domain.mapper.ImageModelMapper;
import br.com.meta.projetoapimeta.domain.repository.ImageModelRepository;
import br.com.meta.projetoapimeta.services.exception.EntityNaoEncontradaException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Tag(name = "Upload de Imagens", description = "Recurso de Upload de Imagens")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/uploads", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageModelControllerImpl {

	private ImageModelRepository imageModelRepository;
	private ImageModelMapper imageModelMapper;

	@PostMapping
	public ResponseEntity<ImageModelResponse> uploadImagem(@RequestParam("imageFile") MultipartFile file)
			throws IOException {

		log.info("POST /upload {}", file.getName().getBytes());

		System.out.println("Tamanho original do byte da imagem - " + file.getBytes().length);

		ImageModelInpuRequest request = new ImageModelInpuRequest(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()), file.getSize());
		ImageModel imageModel = this.imageModelMapper.dtoToEntity(request);
		
		return ResponseEntity.ok().body(this.imageModelMapper.entityToDTO(imageModelRepository.save(imageModel)));

	}
	
	@GetMapping(value = "{idImagem}")
	public ResponseEntity<ImageModelResponse> buscarPorID(@PathVariable Long idImagem) {
		log.info("GET /v1/uploads/{}", idImagem);
		Optional<ImageModel> imagemModelOpt = this.imageModelRepository.findById(idImagem);
		if (!imagemModelOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Imagem de ID não encontrado");
		}

		ImageModelInpuRequest request = new ImageModelInpuRequest(imagemModelOpt.get().getId(),
				imagemModelOpt.get().getNome(), imagemModelOpt.get().getType(),
				decompressBytes(imagemModelOpt.get().getImage()), imagemModelOpt.get().getTamanho());
		ImageModel imageModel = this.imageModelMapper.dtoToEntity(request);
		ImageModelResponse imageModelResponse = this.imageModelMapper.entityToDTO(imageModel);
		return ResponseEntity.ok().body(imageModelResponse);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<ImageModelResponse> getImagem(@RequestParam(value = "nomeImagem") String nomeImagem) throws IOException {
		final Optional<ImageModel> imageModelOpt = this.imageModelRepository.findByNome(nomeImagem);
		if(!imageModelOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Imagem não encontrada");
		}
		
		ImageModelInpuRequest request = new ImageModelInpuRequest(imageModelOpt.get().getId(), imageModelOpt.get().getNome(), imageModelOpt.get().getType(),
				decompressBytes(imageModelOpt.get().getImage()), imageModelOpt.get().getTamanho());
		// new ImageModelInpuRequest(opt.get().getNome(), opt.get().getType(),
		// decompressBytes(opt.get().getType().getBytes()), opt.get().getTamanho());
		ImageModel imageModel = this.imageModelMapper.dtoToEntity(request);
		ImageModelResponse imageModelResponse = this.imageModelMapper.entityToDTO(imageModel);
		return ResponseEntity.ok().body(imageModelResponse);

	}
	
	@GetMapping(value = "/getTest/{nomeImagem}")
	public ResponseEntity<ImageModelResponse> getImagemTest(@PathVariable String nomeImagem) throws IOException {
		final Optional<ImageModel> imageModelOpt = this.imageModelRepository.findByNome(nomeImagem);
		if(!imageModelOpt.isPresent()) {
			throw new EntityNaoEncontradaException("Imagem não encontrada");
		}
		
		ImageModelInpuRequest request = new ImageModelInpuRequest(imageModelOpt.get().getId(), imageModelOpt.get().getNome(), imageModelOpt.get().getType(),
				decompressBytes(imageModelOpt.get().getImage()), imageModelOpt.get().getTamanho());
		ImageModel imageModel = this.imageModelMapper.dtoToEntity(request);
		ImageModelResponse imageModelResponse = this.imageModelMapper.entityToDTO(imageModel);
		return ResponseEntity.ok().body(imageModelResponse);

	}

	// Comprimir os bytes da imagem antes de armazená-los no banco de dados
	public static byte[] compressBytes(byte[] dados) {

		Deflater deflater = new Deflater();

		deflater.setInput(dados);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(dados.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Tamanho do byte da imagem compactada - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// Descompacte os bytes da imagem antes de retorná-los ao aplicativo Angular
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();

	}

}
