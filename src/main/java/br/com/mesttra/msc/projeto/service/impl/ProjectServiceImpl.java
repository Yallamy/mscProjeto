package br.com.mesttra.msc.projeto.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mesttra.msc.projeto.client.BudgetClient;
import br.com.mesttra.msc.projeto.client.SecretariatClient;
import br.com.mesttra.msc.projeto.dto.request.AllocationRequestDTO;
import br.com.mesttra.msc.projeto.dto.response.BudgetResponseDTO;
import br.com.mesttra.msc.projeto.dto.response.SecretariatResponseDTO;
import br.com.mesttra.msc.projeto.entity.Project;
import br.com.mesttra.msc.projeto.exceptions.ApplicationException;
import br.com.mesttra.msc.projeto.exceptions.ServiceEnumValidation;
import br.com.mesttra.msc.projeto.repository.ProjectRepository;
import br.com.mesttra.msc.projeto.service.ProjectService;
import br.com.mesttra.msc.projeto.validation.ValidationCustom;
import lombok.RequiredArgsConstructor;

/**
 * Classe que implementa os métodos do serviço para manter o projeto.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository repository;
	
	private final SecretariatClient secretariatClient;
	
	private final BudgetClient budgetClient;

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.projeto.service.ProjectService#create(br.com.mesttra.msc.projeto.entity.Project)
	 */
	@Override
	public Project create(Project project) throws ApplicationException {
		
		ValidationCustom.validateConsistency(project);
		ValidationCustom.validateDataViolation(project, project.getClass());
		
		SecretariatResponseDTO secretariatResponseDTO = 
				secretariatClient.retrieve(project.getSecretariatId());
		
		ValidationCustom.validateNull(secretariatResponseDTO, ServiceEnumValidation.SECRETARIAT_NOT_FOUND);
		
		//o secretariado está sob investigação
		if(Objects.nonNull(secretariatResponseDTO.getUnderInvestigation()) 
				&& Boolean.TRUE.equals(secretariatResponseDTO.getUnderInvestigation()) ) {
			
			throw new ApplicationException(ServiceEnumValidation.SECRETARIAT_UNDER_INVESTIGATION);
		}
		
		//Um projeto só pode ser aprovado caso haja orçamento disponível para executá-lo
		List<BudgetResponseDTO> budgetResponseDTO = budgetClient.list(project.getFolder().getDestinationType());
		
		if(budgetResponseDTO.isEmpty()) {
			throw new ApplicationException(ServiceEnumValidation.BUDGET_NOT_FOUND);
		}
		
		budgetClient.createBudgetAllocation(budgetResponseDTO.get(0).getId(), 
				AllocationRequestDTO.builder().spentAmount(project.getCost()).build());
		
		return repository.save(project);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.projeto.service.ProjectService#list(br.com.mesttra.msc.projeto.entity.Project, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Project> list(Project project, Pageable pageable) throws ApplicationException {
		
		ValidationCustom.validateConsistency(pageable);

		if(Objects.isNull(project)) {
			project = Project.builder().build();
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<Project> example = Example.of(project, matcher);

		return repository.findAll(example, pageable); 
	}

}
