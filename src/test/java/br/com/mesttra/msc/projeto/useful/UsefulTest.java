package br.com.mesttra.msc.projeto.useful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.projeto.dto.request.ProjectRequestDTO;
import br.com.mesttra.msc.projeto.entity.Project;


/**
 * Classe de teste que representa os cenários de testes da classe {@link Useful}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SuppressWarnings("static-access")
public class UsefulTest {

	@InjectMocks
	private Useful util;
	
	private ProjectRequestDTO source;
	
	@BeforeEach
	public void setup() {

		this.source = 
				ProjectRequestDTO
				.builder()
				.build();
	}

	@Test
	public void convertModelMapperTest() {

		Project entidade = util.convert(source, Project.class);

		ProjectRequestDTO dto = util.convert(entidade, 
				ProjectRequestDTO.class);

		assertNotNull(entidade);
		assertNotNull(dto);
	}

	@Test
	public void convertModelMapperSourceNullTest() {

		Project entidade = util.convert(null, Project.class);

		assertNull(entidade);
	}

	@Test
	public void convertModelMapperDestinationNullTest() {

		Project entidade = util.convert(source, null);

		assertNull(entidade);
	}
	
	@Test
	public void convertModelMapperToListTest() {
		
		List<ProjectRequestDTO> sourceList = 
				new LinkedList<ProjectRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Project> listaEntidade = util.convertToList(sourceList, 
				Project.class);
		
		List<ProjectRequestDTO> listaDTO = util.convertToList(listaEntidade, 
				ProjectRequestDTO.class);
		
		
		assertNotNull(listaEntidade);
		assertNotNull(listaDTO);
	}
	
	@Test
	public void convertModelMapperToListSourceNullTest() {
		
		List<Project> listaEntidade = util.convertToList(null, 
				Project.class);
		
		assertNotNull(listaEntidade);
		assertEquals(0, listaEntidade.size());
	}
	
	@Test
	public void convertModelMapperToListDestinationNullTest() {
		
		List<ProjectRequestDTO> sourceList = 
				new LinkedList<ProjectRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Project> listaEntidade = util.convertToList(sourceList, 
				null);
		
		assertNotNull(listaEntidade);
		assertEquals(0, listaEntidade.size());
	}
	
	@Test
	public void convertModelMapperToPageTest() {
		
		List<ProjectRequestDTO> sourceList = 
				new LinkedList<ProjectRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<ProjectRequestDTO> pageSource = new PageImpl<ProjectRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Project> pageResponse = util.convertToPage(pageSource, 
				Project.class);
		
		assertNotNull(pageResponse);
		assertEquals(1, pageResponse.getContent().size());
	}
	
	@Test
	public void convertModelMapperToPageSourceNullTest() {
		
		Page<Project> pageResponse = util.convertToPage(null, 
				Project.class);
		
		assertNull(pageResponse);
	}
	
	@Test
	public void convertModelMapperToPageDestinationNullTest() {
		
		List<ProjectRequestDTO> sourceList = 
				new LinkedList<ProjectRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<ProjectRequestDTO> pageSource = new PageImpl<ProjectRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Project> pageResponse = util.convertToPage(pageSource, 
				null);
		
		assertNull(pageResponse);
	}
}
