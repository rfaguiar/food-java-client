package com.food.client.api;

import com.food.client.api.exception.ClientApiException;
import com.food.client.model.RestauranteInput;
import com.food.client.model.RestauranteModel;
import com.food.client.model.RestauranteResumoModel;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class RestauranteClient {

    private final static String RESOURCE_PATH = "/restaurantes";
    private final String url;
    private final RestTemplate restTemplate;

    public RestauranteClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<RestauranteResumoModel> listar() {
        try {
            var resourceUrl = url + RESOURCE_PATH;
            var response = restTemplate.getForObject(resourceUrl, RestauranteResumoModel[].class);
            assert response != null;
            return Arrays.asList(response);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

    public RestauranteModel adicionar(RestauranteInput restaurante) {
        var resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate
                    .postForObject(resourceUri, restaurante, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
