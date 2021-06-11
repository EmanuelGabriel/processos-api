package br.com.meta.projetoapimeta.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CRP_UNIDADEGESTORA")
public class UnidadeGestora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 19, updatable = false)
	private Long id;

	@Column(name = "NM_NOME", nullable = false, length = 60)
	private String nome;

	@Column(name = "NM_SIGLA", length = 10)
	private String sigla;

	@Column(name = "NU_CNPJ", length = 14)
	private String cnpj;

	@Column(name = "NU_CODIGO_ESTADO")
	private Integer codigoEstado;

	@Column(name = "FONE", length = 14)
	private String fone;

	@Column(name = "NM_EMAIL")
	private String email;

	@Column(name = "ST_FORMA_PROCESSO")
	private Boolean formaProcesso = false;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_STATUS", nullable = false)
	private Status status;

	@Column(name = "ST_ENTEGA_DOCUMENTO")
	private Boolean entregaDocumento = false;

	@Column(name = "ST_ENTREGA_DOCUMENTO_ELETRONICAMENTE")
	private Boolean entregaDocumentoEletronicamente = false;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_UNIDADEGESTORA", nullable = false)
	private TipoUnidadeGestora tipoUnidadeGestora;

}
