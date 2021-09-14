package br.com.mesttra.msc.projeto.client.fallback;

import org.springframework.stereotype.Component;

import br.com.mesttra.msc.projeto.client.SecretariatClient;
import br.com.mesttra.msc.projeto.dto.response.SecretariatResponseDTO;

/**
 * Classe que implementa o fallback para a classe {@link SecretariatClient} 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Component
public class SecretariatFallback implements SecretariatClient {

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.projeto.client.SecretariatClient#retrieve(java.lang.Long)
	 */
	@Override
	public SecretariatResponseDTO retrieve(Long id) {
		return null;
	}

}
