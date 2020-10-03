package com.food.client.api.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.food.client.model.Problem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientResponseException;

public class ClientApiException extends RuntimeException {

    private final Problem problem;
    private static final Logger LOGGER = LogManager.getLogger(ClientApiException.class);

    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);
        problem = deserializeProblem(cause);
    }

    private Problem deserializeProblem(RestClientResponseException cause) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();
            return objectMapper.readValue(cause.getResponseBodyAsString(), Problem.class);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Nçao foi possivel desserializar a resposa em um problema", e);
        }
        return null;
    }

    public Problem getProblem() {
        return problem;
    }
}
