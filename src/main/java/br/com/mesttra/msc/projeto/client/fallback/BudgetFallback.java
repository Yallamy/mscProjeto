package br.com.mesttra.msc.projeto.client.fallback;

import org.springframework.data.domain.Page;

import br.com.mesttra.msc.projeto.client.BudgetClient;
import br.com.mesttra.msc.projeto.dto.request.AllocationRequestDTO;
import br.com.mesttra.msc.projeto.dto.response.BudgetResponseDTO;

/**
 * Classe que implementa o fallback para a classe {@link BudgetClient} 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
public class BudgetFallback implements BudgetClient {

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.projeto.client.BudgetClient#list(java.lang.String)
	 */
	@Override
	public Page<BudgetResponseDTO> list(String destination) {
		return null;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.projeto.client.BudgetClient#createBudgetAllocation(java.lang.Long, br.com.mesttra.msc.projeto.dto.request.AllocationRequestDTO)
	 */
	@Override
	public BudgetResponseDTO createBudgetAllocation(Long id, AllocationRequestDTO allocation) {
		return null;
	}

}
