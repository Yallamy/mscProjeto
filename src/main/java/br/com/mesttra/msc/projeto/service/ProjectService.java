package br.com.mesttra.msc.projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.projeto.entity.Project;
import br.com.mesttra.msc.projeto.exceptions.ApplicationException;

/**
 * Interface que define os métodos do serviço para manter um {@link Project}.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
public interface ProjectService {

	/**
	 * Método que cria um projeto
	 * @param project
	 * @return Project
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 14 de set de 2021
	 */
	public Project create(Project project) throws ApplicationException;
	
	/**
	 * Método que lista os projetos de acordo com os filtros
	 * @param project - projetos
	 * @param pageable
	 * @return Page<Project>
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 14 de set de 2021
	 */
	public Page<Project> list(Project project, Pageable pageable) throws ApplicationException;
}
