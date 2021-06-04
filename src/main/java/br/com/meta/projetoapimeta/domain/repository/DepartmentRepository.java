package br.com.meta.projetoapimeta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.projetoapimeta.domain.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
