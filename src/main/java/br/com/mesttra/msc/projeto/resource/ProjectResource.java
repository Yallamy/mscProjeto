package br.com.mesttra.msc.projeto.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mesttra.msc.projeto.dto.request.ProjectRequestDTO;
import br.com.mesttra.msc.projeto.dto.response.ProjectResponseDTO;
import br.com.mesttra.msc.projeto.entity.Project;
import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import br.com.mesttra.msc.projeto.exceptions.ApplicationException;
import br.com.mesttra.msc.projeto.service.ProjectService;
import br.com.mesttra.msc.projeto.useful.ConstantsPath;
import br.com.mesttra.msc.projeto.useful.ConstantsSwagger;
import br.com.mesttra.msc.projeto.useful.Useful;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Classe que disponibiliza os serviços para manter o projeto.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value= ConstantsPath.PATH_PROJECT, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantsPath.PATH_PROJECT, produces = MediaType.APPLICATION_JSON_VALUE, tags = { ConstantsPath.TAG_PROJECT })
public class ProjectResource {

	private final ProjectService service;

	/**
	 * Método REST que cria um projeto.
	 * @param request - ProjectRequestDTO
	 * @return ResponseEntity<?> - projeto criado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws ApplicationException 
	 * @since 14 de set de 2021
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = ConstantsSwagger.CREATE_PROJECT, 
		notes = ConstantsSwagger.CREATE_PROJECT_NOTES, response = ProjectResponseDTO.class)
	public @ResponseBody ResponseEntity<?> create(
			@Valid @RequestBody ProjectRequestDTO request) throws ApplicationException {

		Project project = Useful.convert(request, Project.class);
		project.setFolder(DestinationTypeEnum.getEnum(request.getFolder()));

		project = this.service.create(project);
		ProjectResponseDTO response = Useful.convert(project, ProjectResponseDTO.class);

		return new ResponseEntity<ProjectResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que lista os projetos de acordo com os filtros informados.
	 * @param folder - filtro folder do projeto
	 * @param secretariatId - filtro secretariatId do projeto
	 * @return ResponseEntity<?> - lista de projetos ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws ApplicationException 
	 * @since 14 de set de 2021
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = ConstantsSwagger.LIST_PROJECT, 
		notes = ConstantsSwagger.LIST_PROJECT_NOTES, response = ProjectResponseDTO.class)
	public @ResponseBody ResponseEntity<Page<?>> list(
			@PageableDefault(value = 30, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam("folder") Optional<String> folder,
			@RequestParam("secretariatId") Optional<Long> secretariatId) throws ApplicationException {

		Project secretariat = 
				Project
				.builder()
				.folder(DestinationTypeEnum.getEnum(folder.orElse(null)))
				.secretariatId(secretariatId.orElse(null))
				.build();

		Page<Project> page = this.service.list(secretariat, pageable);
		Page<ProjectResponseDTO> response = Useful.convertToPage(page, ProjectResponseDTO.class);

		return ResponseEntity.ok(response);
	}
}
