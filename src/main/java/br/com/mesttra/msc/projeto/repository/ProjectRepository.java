package br.com.mesttra.msc.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mesttra.msc.projeto.entity.Project;

/**
 * Repositório da entidade {@link Project}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
