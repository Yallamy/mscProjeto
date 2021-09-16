package br.com.mesttra.msc.projeto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.projeto.EntityGenericUtil;
import br.com.mesttra.msc.projeto.client.BudgetClient;
import br.com.mesttra.msc.projeto.client.SecretariatClient;
import br.com.mesttra.msc.projeto.dto.request.AllocationRequestDTO;
import br.com.mesttra.msc.projeto.dto.response.BudgetResponseDTO;
import br.com.mesttra.msc.projeto.dto.response.SecretariatResponseDTO;
import br.com.mesttra.msc.projeto.entity.Project;
import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import br.com.mesttra.msc.projeto.exceptions.ApplicationException;
import br.com.mesttra.msc.projeto.repository.ProjectRepository;

/**
 * Classe de teste que representa os cenários de testes da classe {@link ProjectServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 15 de set de 2021
 */
@SpringBootTest
public class ProjectServiceImplTest {

	@InjectMocks
	private ProjectServiceImpl service;

	@Mock
	private ProjectRepository repository;
	
	@Mock
	private SecretariatClient secretariatClient;
	
	@Mock
	private BudgetClient budgetClient;

	@Mock
	private Pageable pageable;

	@Mock
	private Page<Project> page;

	private Project request;

	private Project response;
	
	private List<Project> listaResponse;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {

		this.request = Project
				.builder()
				.secretariatId(EntityGenericUtil.getLong())
				.cost(EntityGenericUtil.getBigDecimal())
				.title(EntityGenericUtil.getString(15))
				.description(EntityGenericUtil.getString(30))
				.folder(DestinationTypeEnum.EDUCATION)
				.build();

		this.response = Project
				.builder()
				.id(EntityGenericUtil.getLong())
				.secretariatId(EntityGenericUtil.getLong())
				.cost(EntityGenericUtil.getBigDecimal())
				.title(EntityGenericUtil.getString(15))
				.description(EntityGenericUtil.getString(30))
				.folder(DestinationTypeEnum.EDUCATION)
				.build();
		
		listaResponse = new LinkedList<Project>();
		listaResponse.add(this.response);
		
		BudgetResponseDTO budgetResponseDTO = BudgetResponseDTO.builder().build();
		SecretariatResponseDTO secretariatResponseDTO = SecretariatResponseDTO.builder().build();
		List<BudgetResponseDTO> listBudgetResponseDTO = new LinkedList<BudgetResponseDTO>();
		listBudgetResponseDTO.add(budgetResponseDTO);

		Mockito.when(this.repository.save(
				Mockito.any(Project.class))).thenReturn(this.response);
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
		Mockito.when(this.page.getContent()).thenReturn(this.listaResponse);
		Mockito.when(this.secretariatClient.retrieve(
				Mockito.any(Long.class))).thenReturn(secretariatResponseDTO);
		Mockito.when(this.budgetClient.list(
				Mockito.any(String.class))).thenReturn(listBudgetResponseDTO);
		Mockito.when(this.budgetClient.createBudgetAllocation(
				Mockito.any(Long.class), Mockito.any(AllocationRequestDTO.class))).thenReturn(budgetResponseDTO);
	}

	//create
	@Test
	public void createTest() {
		
		Project response = this.service.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.response, response);
	}
	
	@Test()
	public void createSecretariatUnderInvestigationTest() {

		SecretariatResponseDTO secretariatResponseDTO = 
				SecretariatResponseDTO
				.builder()
				.underInvestigation(Boolean.TRUE)
				.build();
		
		Mockito.when(this.secretariatClient.retrieve(
				Mockito.any(Long.class))).thenReturn(secretariatResponseDTO);
		
		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	
	@Test()
	public void createBudgetNotFoundTest() {
		
		List<BudgetResponseDTO> listBudgetResponseDTO = new LinkedList<BudgetResponseDTO>();
		
		Mockito.when(this.budgetClient.list(
				Mockito.any(String.class))).thenReturn(listBudgetResponseDTO);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	

	@Test()
	public void createProjectNullTest() {

		assertThrows(ApplicationException.class, () -> {
			this.service.create(null);
		});
	}
	
	@Test()
	public void createSecretariatIdNullTest() {

		this.request.setSecretariatId(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	
	@Test()
	public void createCostNullTest() {

		this.request.setCost(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	
	@Test()
	public void createTitleNullTest() {

		this.request.setTitle(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	
	@Test()
	public void createDescriptionNullTest() {

		this.request.setDescription(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}

	@Test()
	public void createFolderNullTest() {

		this.request.setFolder(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}

	//list
	@Test
	public void listTest() {

		Project request = Project.builder()
				.build();

		Page<Project> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listComProjectNullTest() {

		Page<Project> response = this.service.list(null, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test()
	public void listComPageableNullTest() {

		assertThrows(ApplicationException.class, () -> {
			this.service.list(this.request, null);
		});
	}

	@Test
	public void listPorFolderTest() {

		Project request = Project.builder()
				.folder(DestinationTypeEnum.HEALTH)
				.build();

		Page<Project> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listPorSecretariatIdTest() {

		Project request = Project.builder()
				.secretariatId(EntityGenericUtil.getLong())
				.build();

		Page<Project> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
}
