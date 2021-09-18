package br.com.mesttra.msc.projeto.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.mesttra.msc.projeto.dto.response.ErrorDTO;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementa o ErrorDecoder do Feign personalizado para o httpCode
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 18 de set de 2021
 */
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
	
	private final ObjectMapper mapper;

    public CustomErrorDecoder() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

	/*
	 * 
	 * (non-Javadoc)
	 * @see feign.codec.ErrorDecoder#decode(java.lang.String, feign.Response)
	 */
	@Override
	public ApplicationException decode(String methodKey, Response response) {
		
		ErrorDTO error = ErrorDTO.builder().errorCode("Erro de comunicação").build();

	    try {
	    	error = mapper.readValue(response.body().toString(), ErrorDTO.class);
	    } catch (Exception e) {
	    	log.error("falha ao recuperar o conteúdo do erro");
	    }
	    
		log.error("methodKey: " + methodKey);
		log.error("response status: " + response.status());
		log.error("response error: " + error);
		
		switch (response.status()){
		case 400:
			return new ApplicationException(ServiceEnumValidation.EXTERNAL_COMMUNICATION_FAILURE, 
					error.getErrorCode());
		case 404:
			return new ApplicationException(ServiceEnumValidation.EXTERNAL_COMMUNICATION_FAILURE, 
					error.getErrorCode());
		default:
			return new ApplicationException(ServiceEnumValidation.EXTERNAL_COMMUNICATION_FAILURE, 
					error.getErrorCode());
		}
	}

}
