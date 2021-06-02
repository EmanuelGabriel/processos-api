package br.com.meta.projetoapimeta.persistence.entity;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "tb_localizacao")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "street_address", nullable = false, length = 40)
	private String addressStreet;

	@Column(name = "postal_code", nullable = false, length = 12)
	private double codePostal;

	@Column(name = "city", nullable = false, length = 30)
	private String city;

	@Column(name = "state_province", nullable = false, length = 25)
	private String stateProvince;

	@OneToMany(mappedBy = "location")
	private List<Department> departments;

	/**
	 * Pegar um objeto de referência e mapear para um objeto do tipo 'Location'
	 * 
	 * @param function
	 * @param <R>
	 * @return
	 */
	public <R> R map(Function<Location, R> function) {
		return function.apply(this);
	}
}
