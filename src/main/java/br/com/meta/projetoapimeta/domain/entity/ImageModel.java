package br.com.meta.projetoapimeta.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_image_model")
public class ImageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_image", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, length = 90)
	private String nome;

	@Column(name = "type", nullable = false)
	private String type;

	// Os bytes da imagem podem ter comprimentos grandes, então especificamos um
	// valor que é mais do que o comprimento padrão para a coluna
	@Lob
	@Column(name = "image", nullable = false, length = 1000)
	private byte[] image;

	private long tamanho;

	@ManyToOne
	@JoinColumn(name = "fk_processo")
	private Processo processo;

	public ImageModel(String nome, String type, byte[] image) {
		this.nome = nome;
		this.type = type;
		this.image = image;
	}

}
