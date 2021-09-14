package br.com.mesttra.msc.projeto.dto.response;

import java.io.Serializable;

import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados do response do secretariado para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretariatResponseDTO implements Serializable {

	private static final long serialVersionUID = 8692009026050331917L;
	
	private Long id;
	
	private DestinationTypeEnum folder;
	
	private String secretary;
	
	private Integer populationGrade;
	
	private Boolean underInvestigation;
}
