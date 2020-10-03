package com.food.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record RestauranteResumoModel(@JsonProperty("id") Long id,
                                     @JsonProperty("nome") String nome,
                                     @JsonProperty("taxaFrete") BigDecimal taxaFrete,
                                     @JsonProperty("cozinha") CozinhaModel cozinha) {
}
