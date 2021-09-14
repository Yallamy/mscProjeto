package br.com.mesttra.msc.projeto.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de response da alocação de orçamento para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocationResponseDTO implements Serializable {

	private static final long serialVersionUID = 5286594258994034207L;
	
	private Long id;
	
	private BigDecimal spentAmount;
	
	private LocalDateTime dtAllocation;
	
}
