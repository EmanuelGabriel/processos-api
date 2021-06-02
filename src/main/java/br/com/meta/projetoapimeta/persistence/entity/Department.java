package br.com.meta.projetoapimeta.persistence.entity;

import java.io.Serializable;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_departamentos")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "department_name", nullable = false, length = 30)
	private String name;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	/**
	 * Pegar um objeto de referência e mapear para um objeto do tipo 'Department'
	 * 
	 * @param function
	 * @param <R>
	 * @return
	 */
	public <R> R map(Function<Department, R> function) {
		return function.apply(this);
	}

}
