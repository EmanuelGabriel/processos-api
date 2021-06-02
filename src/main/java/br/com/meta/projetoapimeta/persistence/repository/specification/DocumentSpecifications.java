package br.com.meta.projetoapimeta.persistence.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.meta.projetoapimeta.persistence.entity.Document;

public class DocumentSpecifications {

	public static Specification<Document> likeNomeDocumento(String nomeDocumento) {
		if (nomeDocumento == null) {
			return null;
		}

		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("nomeDocumento"), "%".concat(nomeDocumento).concat("%"));
		};
	}

	public static Specification<Document> getNumeroProcesso(Integer numeroProcesso) {
		if (numeroProcesso == null) {
			return null;
		}

		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("numeroProcesso"), numeroProcesso);
		};
	}

	public static Specification<Document> buscarPorDataEstimadaConclusao(LocalDate dataEstimadaConclusaoInicio,
			LocalDate dataEstimadaConclusaoFim) {

		if (dataEstimadaConclusaoInicio == null && dataEstimadaConclusaoFim == null) {
			return null;
		}

		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("dataEstimadaConclusao")));
			return criteriaBuilder.between(root.get("dataEstimadaConclusao"), dataEstimadaConclusaoInicio, dataEstimadaConclusaoFim);
		};
	}

}
