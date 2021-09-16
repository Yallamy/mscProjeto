package br.com.mesttra.msc.projeto.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.mesttra.msc.projeto.useful.ConstantsSwagger;
import br.com.mesttra.msc.projeto.useful.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de request do projeto para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = ConstantsSwagger.PROJECT_REQUEST_DTO)
public class ProjectRequestDTO implements Serializable {
	
	private static final long serialVersionUID = -6863617967549263692L;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_REQUEST_SECRETARIAT_ID_DTO, position = 1)
	@NotNull(message = Message.SECRETARIAT_ID_REQUIRED)
	private Long secretariatId;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_REQUEST_COST_DTO, position = 2)
	@NotNull(message = Message.COST_REQUIRED)
	private BigDecimal cost;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_REQUEST_TITLE_DTO, position = 3)
	@NotNull(message = Message.TITLE_REQUIRED)
	private String title;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_REQUEST_DESCRIPTION_DTO, position = 4)
	@NotNull(message = Message.DESCRIPTION_REQUIRED)
	private String description;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_REQUEST_FOLDER_DTO, position = 5)
	@NotNull(message = Message.FOLDER_REQUIRED)
	private String folder;

}
