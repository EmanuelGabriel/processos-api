package br.com.meta.projetoapimeta.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.meta.projetoapimeta.domain.enumerator.TipoSituacao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_documentos")
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_document", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome_documento", nullable = false, length = 100)
	private String nomeDocumento;

	@Column(name = "numero_processo", nullable = false, precision = 10, scale = 2)
	private Integer numeroProcesso;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@Column(name = "data_estimada_conclusao")
	private LocalDate dataEstimadaConclusao;

	@Enumerated(EnumType.STRING)
	private TipoSituacao situacao;

	/**
	 * Pegar um objeto de referência e mapear para um objeto do tipo 'Document'
	 * 
	 * @param function
	 * @param <R>
	 * @return
	 */
	public <R> R map(Function<Document, R> function) {
		return function.apply(this);
	}

}
