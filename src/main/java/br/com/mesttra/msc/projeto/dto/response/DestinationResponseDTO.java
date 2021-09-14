package br.com.mesttra.msc.projeto.dto.response;

import java.io.Serializable;

import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de response do destino de orçamento para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationResponseDTO implements Serializable {

	private static final long serialVersionUID = -8951645012997946800L;
	
	private Long id;
	
	private DestinationTypeEnum destinationType;
}
