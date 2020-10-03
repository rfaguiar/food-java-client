package com.food.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Problem(@JsonProperty("status") Integer status,
                      @JsonProperty("timestamp") LocalDateTime timestamp,
                      @JsonProperty("userMessage") String userMessage) {
}
