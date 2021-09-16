package br.com.mesttra.msc.projeto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mesttra.msc.projeto.client.fallback.SecretariatFallback;
import br.com.mesttra.msc.projeto.dto.response.SecretariatResponseDTO;

/**
 * Interface que define um cliente para o serviço de secretariado
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@FeignClient(name = "mcsSecretaria", url="http://localhost:8082/", fallback = SecretariatFallback.class)
public interface SecretariatClient {

	@RequestMapping(method = RequestMethod.GET, 
			value = "/microservices/api/v1/mesttra/secretariat/{id}", 
			consumes = "application/json")
	public SecretariatResponseDTO retrieve(@PathVariable("id") Long id);
}
