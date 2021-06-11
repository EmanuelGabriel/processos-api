package br.com.meta.projetoapimeta.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeGestoraModelResponse {

	private Long id;

	private String nome;

	private String sigla;

	private String cnpj;

	private Integer codigoEstado;

	private String fone;

	private String email;

	private Boolean formaProcesso;

	private StatusModelResponse status;

	private Boolean entregaDocumento;

	private Boolean entregaDocumentoEletronicamente;

	private TipoUnidadeGestoraModelResponse tipoUnidadeGestora;

}
