package br.com.mesttra.msc.projeto.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import br.com.mesttra.msc.projeto.useful.ConstantsSwagger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de response do projeto para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = ConstantsSwagger.PROJECT_RESPONSE_DTO)
public class ProjectResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 5213900916205086369L;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_ID_DTO, position = 1)
	private Long id;

	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_SECRETARIAT_ID_DTO, position = 2)
	private Long secretariatId;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_COST_DTO, position = 3)
	private BigDecimal cost;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_TITLE_DTO, position = 4)
	private String title;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_DESCRIPTION_DTO, position = 5)
	private String description;
	
	@ApiModelProperty(value = ConstantsSwagger.PROJECT_RESPONSE_FOLDER_DTO, position = 6)
	private DestinationTypeEnum folder;

}
