package br.com.meta.projetoapimeta.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.meta.projetoapimeta.domain.enumerator.FaseProcesso;
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
@Table(name = "tb_processo")
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_processo", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nu_protocolo", nullable = false)
	private Integer numeroProtocolo;

	@Column(name = "dt_inicioprocesso", nullable = false)
	private LocalDateTime dataInicioProcesso;

	@Column(name = "dt_atualizacaoprocesso", nullable = false)
	private LocalDateTime dataAtualizacaoProcesso;

	@Column(name = "nu_processosei", nullable = false)
	private Integer numeroProcessoSei;

	@Column(name = "dt_prazoestimado", nullable = false)
	private LocalDateTime dataPrazoEstimado;

	@Column(name = "nu_cpflogado", nullable = false, length = 11)
	private String cpf;

	@Column(name = "ds_faseProcessual")
	@Enumerated(EnumType.STRING)
	private FaseProcesso faseProcessual;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_situacao", nullable = false)
	private SituacaoProcesso situacaoProcesso;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ImageModel> images = new ArrayList<>();

}
