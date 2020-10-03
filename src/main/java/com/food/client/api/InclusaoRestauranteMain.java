package com.food.client.api;

import com.food.client.api.exception.ClientApiException;
import com.food.client.model.CidadeIdInput;
import com.food.client.model.CozinhaIdInput;
import com.food.client.model.EnderecoInput;
import com.food.client.model.RestauranteInput;
import com.food.client.model.RestauranteModel;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        try {
            var restTemplate = new RestTemplate();
            var restauranteClient = new RestauranteClient(
                    "http://localhost:8080", restTemplate);

            var cozinha = new CozinhaIdInput();
            cozinha.setId(1L);

            var cidade = new CidadeIdInput();
            cidade.setId(1L);

            var endereco = new EnderecoInput();
            endereco.setCidade(cidade);
            endereco.setCep("38500-111");
            endereco.setLogradouro("Rua Xyz");
            endereco.setNumero("300");
            endereco.setBairro("Centro");

            var restaurante = new RestauranteInput();
            restaurante.setNome("Comida Mineira");
            restaurante.setTaxaFrete(new BigDecimal("9.5"));
            restaurante.setCozinha(new CozinhaIdInput());
            restaurante.setCozinha(cozinha);
            restaurante.setEndereco(endereco);

            RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);

            System.out.println(restauranteModel);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().userMessage());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
