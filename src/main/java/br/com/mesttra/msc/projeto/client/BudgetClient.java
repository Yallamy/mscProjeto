package br.com.mesttra.msc.projeto.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mesttra.msc.projeto.client.fallback.BudgetFallback;
import br.com.mesttra.msc.projeto.dto.request.AllocationRequestDTO;
import br.com.mesttra.msc.projeto.dto.response.BudgetResponseDTO;

/**
 * Interface que define um cliente para o serviço de orçamento
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@FeignClient(name = "mcsOrcamento", url="http://localhost:8081/", fallback = BudgetFallback.class)
public interface BudgetClient {
	
	@RequestMapping(method = RequestMethod.GET, 
			value = "/microservices/api/v1/mesttra/budget/", 
			consumes = "application/json")
	public List<BudgetResponseDTO> list(
			@RequestParam("destination") String destination);
	
	@RequestMapping(method = RequestMethod.POST, 
			value = "/microservices/api/v1/mesttra/budget/{id}", 
			consumes = "application/json")
	public BudgetResponseDTO createBudgetAllocation(
			@PathVariable("id") Long id,
			AllocationRequestDTO allocation);
	

}
